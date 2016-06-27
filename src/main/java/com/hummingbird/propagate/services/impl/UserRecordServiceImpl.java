package com.hummingbird.propagate.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.common.util.CreateGUID;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.propagate.entity.Article;
import com.hummingbird.propagate.entity.ArticlePropagate;
import com.hummingbird.propagate.entity.ArticleTag;
import com.hummingbird.propagate.entity.ReadArticle;
import com.hummingbird.propagate.entity.ShareArticle;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.mapper.ArticleMapper;
import com.hummingbird.propagate.mapper.ArticlePropagateMapper;
import com.hummingbird.propagate.mapper.ArticleTagMapper;
import com.hummingbird.propagate.mapper.ReadArticleMapper;
import com.hummingbird.propagate.mapper.ShareArticleMapper;
import com.hummingbird.propagate.services.ArticleService;
import com.hummingbird.propagate.services.ArticleTagService;
import com.hummingbird.propagate.services.TokenService;
import com.hummingbird.propagate.services.UserRecordService;
import com.hummingbird.propagate.services.UserTagService;
import com.hummingbird.propagate.services.WxUserService;
import com.hummingbird.propagate.vo.SaveArticleVO;
import com.hummingbird.propagate.vo.SaveReadArticleVO;
import com.hummingbird.propagate.vo.SaveShareArticleVO;
import com.hummingbird.propagate.vo.UserVO;


@Service
public class UserRecordServiceImpl implements UserRecordService{

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	TokenService tokenSrv;
	@Autowired
	WxUserService wxUserService;
	@Autowired
	ArticleService articleService;
	@Autowired
	UserTagService userTagService;
	@Autowired
	ArticleTagService articleTagService;
	@Autowired
	ReadArticleMapper readArticleDao;
	@Autowired
	ShareArticleMapper shareArticleDao;
	@Autowired
	ArticlePropagateMapper articlePropagateDao;
	@Autowired
	ArticleMapper articleDao;
	@Autowired
	ArticleTagMapper articleTagDao;
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "txManager")
	public String saveReadArticleRecord(SaveReadArticleVO vo) throws BusinessException {
		
		String jsScript =  "测试js内容";//loadJS();  
		
		String openId = vo.getOpenId();
		String articleId = vo.getArticleId();
		String originalOpenId = vo.getOriginalOpenId();
		if(openId != null && articleId!=null){
			try{
				Integer originalUserId = null;
				if(StringUtils.isNotBlank(originalOpenId)){
					originalUserId = wxUserService.selectUserIdByOpenId(originalOpenId);
				}
				WxUser readUser = wxUserService.selectUserByOpendId(openId);
				ReadArticle readArticle = new ReadArticle();
				 //保存文章阅读记录
				readArticle.setUserid(readUser.getUserid());
				readArticle.setArticleId(articleId);
				readArticle.setOriginalUrl(vo.getOriginalUrl());
				readArticle.setOriginalUserid(originalUserId);
				readArticle.setInsertTime(new Date());
				 readArticleDao.insert(readArticle);
				 
				 //保存传播关系
				 saveArticlePropagate(readUser.getUserid(),readUser.getNickname(), articleId,originalUserId);
				 
				 //保存用户标签  阅读 数目
				 userTagService.saveUserTag("read",articleId, readUser.getUserid());
			}catch(DataAccessException e){
				e.printStackTrace();
				log.debug("保存文章阅读记录失败。");
			}
		}
		return jsScript;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "txManager")
	public void saveArticle(SaveArticleVO vo) throws BusinessException {
		String openId = vo.getOpenId();
	    String title = vo.getTitle();
	    String tagIds = vo.getTagIds();
	    if(StringUtils.isBlank(openId)){
	       throw new BusinessException("openId不能为空。");
	    }
		ValidateUtil.assertNullnoappend(title, "标题不能为空。");	
		
		Article article = null;
		Integer userid = null;
		try{
			userid = wxUserService.selectUserIdByOpenId(openId);
			String articleId = vo.getArticleId();
			if(StringUtils.isBlank(articleId)){
				//用户没有传文章的id，则生成默认的id
				articleId = CreateGUID.createGuId();
			}else{
				article = articleDao.selectByPrimaryKey(articleId);
			}
            if(article == null){
            	article =new Article();
            	article.setUserid(userid);
				article.setId(articleId);
            	article.setTitle(title);
                article.setContent(vo.getContent());
                article.setStatus("OK#");
            	article.setInsertTime(new Date());
                article.setUpdateTime(new Date());
    			articleDao.insert(article);
            }else{
            	article.setTitle(title);
                article.setContent(vo.getContent());
                article.setStatus("OK#");
                article.setUpdateTime(new Date());
            	articleDao.updateByPrimaryKey(article);
            }
			if(StringUtils.isNotBlank(tagIds)){
				 //保存文章标签信息
				 saveArticleTags(vo.getTagNames(), tagIds, articleId);
			}
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("保存文章阅读记录失败。");
		}catch(BusinessException e){
			e.printStackTrace();
			throw e;
		}
	}

    /**
     * 保存文章标签信息
     * @param tagNameStr
     * @param tagIds
     * @param articleId
     */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "txManager")
	private void saveArticleTags(String tagNameStr, String tagIds, String articleId) {
		String[] tags = tagIds.split("&");
		String[] tagNames = tagNameStr.split("&");
		String tagName = null;
		ArticleTag articleTag = new ArticleTag();
		articleTag.setArticleId(articleId);
		int count = tags.length;
		for(int i = 0;i<count; i++){
			try{
				tagName = tagNames[i];
			}catch(ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
				log.debug("保存标签时，用&分隔的标签名称数目比标签id少，导致数组越界。");
			}
			articleTag.setTagId(Integer.parseInt(tags[i]));
			articleTag.setTagName(tagName);
			articleTagDao.insert(articleTag);
		}
	}

	/**
	 * 保存传播关系
	 * @param userid
	 * @param articleId
	 * @param originalUserid
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "txManager")
	private void saveArticlePropagate(Integer userid,
			 String nickName,String articleId ,Integer originalUserid) {
		 
		Article article = null;
		try {
			article = articleService.selectArticleById(articleId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		if(article != null){
			if(StringUtils.isBlank(nickName)){
				nickName = "匿名";
			}
			ArticlePropagate pro = articlePropagateDao.selectByUserIdAndArticleId(userid,articleId);
			if(pro == null){
				pro = new ArticlePropagate();
				pro.setUserid(userid);
				pro.setName(nickName);
				pro.setArticleId(articleId);
				pro.setStatus("OK#");
				pro.setArticleName(article.getTitle());
				pro.setParentId(originalUserid==null?0:originalUserid);
				pro.setInsertTime(new Date());
				articlePropagateDao.insert(pro);
			}else{
				pro.setStatus("OK#");
				pro.setArticleName(article.getTitle());
				pro.setParentId(originalUserid==null?0:originalUserid);
				pro.setName(nickName);
				articlePropagateDao.updateByPrimaryKey(pro);
			}
		}
		
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, value = "txManager")
	public String saveShareArticleRecord(SaveShareArticleVO vo) throws BusinessException {
		
		String jsScript = "测试js内容";//loadJS();  
		
		String openId = vo.getOpenId();
		String articleId = vo.getArticleId();
		String originalOpenId = vo.getOriginalOpenId();
		if(StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(articleId) 
				&& StringUtils.isNotBlank(originalOpenId)){
		   try{
					Integer originalUserId =  wxUserService.selectUserIdByOpenId(originalOpenId);
					WxUser readUser = wxUserService.selectUserByOpendId(openId);
			  if(readUser != null && vo.getArticleId()!=null && originalUserId!=null){
				try{
					//保存传播关系
					saveArticlePropagate(readUser.getUserid(),readUser.getNickname(), articleId,originalUserId);
					
					ShareArticle shareArticle = new ShareArticle();
					//保存文章分享记录
					shareArticle.setUserid(readUser.getUserid());
					shareArticle.setOriginalUserid(originalUserId);
					shareArticle.setArticleId(articleId);
					shareArticle.setOriginalUrl(vo.getOriginalUrl());
					shareArticle.setShareTarget(vo.getShareTarget());
					shareArticle.setShareType(vo.getShareType());
					shareArticle.setShareTime(new Date());
					shareArticle.setInsertTime(new Date());
					shareArticleDao.insert(shareArticle);
					
					 //更新用户 标签  分享  数目
					 userTagService.saveUserTag("share",articleId, readUser.getUserid());
				}catch(DataAccessException e){
					e.printStackTrace();
					log.debug("保存文章分享记录。");
				}
			  }
		    }catch(Exception e){
				e.printStackTrace();
			}
		}
		return jsScript;
	}
	
	/**
	 * 加载js内容
	 * @return
	 * @throws BusinessException
	 */
	private String loadJS(){
		List<String> lines = null;
		String filePath = "C:\\js\\userrecord.js";
		try {
		    File file=new File(filePath);
		    //判断文件是否存在
            if(!file.isFile() || !file.exists()){ 
            	log.debug("找不到指定的文件。");
            	//throw  new BusinessException("找不到指定的文件。");
            }
			lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			//throw new BusinessException("读取文件失败："+e.getMessage());
		}  
        StringBuilder sb = new StringBuilder();  
        for(String line : lines){  
           sb.append(line);  
         }  
		return sb.toString();
	}

	@Override
	public String saveUserInfo(WxUser wxUser){
		String jsScript = "";
		try{
			 //加载js内容
			 jsScript =  "测试js内容";//loadJS();  
			 
			 WxUser isExistUser = null;
			 if(StringUtils.isNotBlank(wxUser.getOpenid())){
				 //根据openid查询用户信息
				 isExistUser = wxUserService.selectUserByOpendId(wxUser.getOpenid());
				 if(isExistUser == null){
					 //保存微信用户信息
					 wxUserService.addWxUserInfo(wxUser);
				 }else{
					 wxUser.setUserid(isExistUser.getUserid());
					 //更新微信用户信息
					 wxUserService.updateByPrimaryKey(wxUser);
				 }
			 }
		}catch(Exception e){
			e.printStackTrace();
			log.debug("保存用户浏览记录失败。");
		}
		return jsScript;
	}

	@Override
	public String saveUserInfo(UserVO userVO) throws BusinessException {
		String jsScript = "";
		try{
			 //加载js内容
			 jsScript =  "测试js内容";//loadJS();  
			 
			 if(StringUtils.isNotBlank(userVO.getOpenid())){
				 WxUser user = new WxUser();
				 user.setUnionid(userVO.getUnionid());
				 user.setCity(userVO.getCity());
				 user.setCountry(userVO.getCountry());
				 user.setHeadimgurl(userVO.getHeadimgurl());
				 user.setLanguage(userVO.getLanguage());
				 user.setNickname(userVO.getNickname());
				 user.setOpenid(userVO.getOpenid());
				 user.setProvince(userVO.getProvince());
				 user.setRemark(userVO.getRemark());
				 user.setTagidList(userVO.getTagidList());
                 if(StringUtils.isNotBlank(userVO.getGroupid())){
    				 user.setGroupid(Integer.parseInt(userVO.getGroupid()));
                 }
                 if(StringUtils.isNotBlank(userVO.getQrExpireSeconds())){
    				 user.setQrExpireSeconds(Integer.parseInt(userVO.getQrExpireSeconds()));
                 }
                 if(StringUtils.isNotBlank(userVO.getQrCreateTime())){
    				 user.setGroupid(Integer.parseInt(userVO.getQrCreateTime()));
                 }
                 if(StringUtils.isNotBlank(userVO.getSex())){
    				 user.setSex(Byte.parseByte(userVO.getSex()));
                 }
                 if(StringUtils.isNotBlank(userVO.getSubscribeTime())){
    				 user.setSubscribeTime(Integer.parseInt(userVO.getSubscribeTime()));
                 }
                 if(StringUtils.isNotBlank(userVO.getSubscribe())){
    				 user.setSubscribe(Byte.parseByte(userVO.getSubscribe()));
                 }
				 
				 WxUser isExistUser = null;
				 //根据openid查询用户信息
				 isExistUser = wxUserService.selectUserByOpendId(userVO.getOpenid());
				 if(isExistUser == null){
					 //保存微信用户信息
					 wxUserService.addWxUserInfo(user);
				 }else{
					 user.setUserid(isExistUser.getUserid());
					 //更新微信用户信息
					 wxUserService.updateByPrimaryKey(user);
				 }
			 }
		}catch(Exception e){
			e.printStackTrace();
			log.debug("保存用户浏览记录失败。");
		}
		return jsScript;
	}
	
	  public static void main(String[] args)throws Exception{  
		  String fromFile = "";
		  // Java7 : Files.readAllBytes默认以UTF-8编码读入文件，故文件的编码如果不是UTF-8，那么中文内容会出现乱字符  
		  try {
			fromFile = new String(Files.readAllBytes(Paths.get("C:\\js\\userrecord.js")));
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("java7读取文件失败。");
		}  
          System.out.println("Java7默认以UTF-8编码读入文件，故文件的编码如果不是UTF-8，那么中文内容会出现乱字符  :");   
		  System.out.println(fromFile);  
		  
	      // Java8用流的方式读文件，更加高效
	      List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get("C:\\js\\userrecord.js"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("java8读取文件失败。");
		}  
          StringBuilder sb = new StringBuilder();  
          for(String line : lines){  
             sb.append(line);  
          }  
          fromFile = sb.toString(); 
          System.out.println("Java8用流的方式读文件:");   
         System.out.println(fromFile);  
   }
	  
	  public static String readTxtFile(String filePath){
		  String target = "";
	        try {
	                String encoding="UTF-8";
	                File file=new File(filePath);
	                if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
                        StringBuilder text = new StringBuilder();
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                    	text.append(lineTxt);
	                    }
	                    read.close();
	                    target =  text.toString();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
	        } catch (Exception e) {
	            System.out.println("读取文件内容出错");
	            e.printStackTrace();
	        }
	        return target;
	    }

}
