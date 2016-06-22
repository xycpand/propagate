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

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.entity.Article;
import com.hummingbird.propagate.entity.ArticlePropagate;
import com.hummingbird.propagate.entity.ReadArticle;
import com.hummingbird.propagate.entity.ShareArticle;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.mapper.ArticlePropagateMapper;
import com.hummingbird.propagate.mapper.ReadArticleMapper;
import com.hummingbird.propagate.mapper.ShareArticleMapper;
import com.hummingbird.propagate.services.ArticleService;
import com.hummingbird.propagate.services.TokenService;
import com.hummingbird.propagate.services.UserRecordService;
import com.hummingbird.propagate.services.WxUserService;

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
	ReadArticleMapper readArticleDao;
	@Autowired
	ShareArticleMapper shareArticleDao;
	@Autowired
	ArticlePropagateMapper articlePropagateDao;
	
	@Override
	public String saveReadArticleRecord(ReadArticle vo) throws BusinessException {
		
		String jsScript = loadJS();  
		Integer userid = vo.getUserid();
		String articleId = vo.getArticleId();
		Integer originalUserId = vo.getOriginalUserid();
		if(userid != null && articleId!=null){
			try{
				
				saveArticlePropagate(userid, articleId,originalUserId);
				
				 vo.setInsertTime(new Date());
				 readArticleDao.insert(vo);
			}catch(DataAccessException e){
				e.printStackTrace();
				log.debug("保存文章阅读记录失败。");
			}
		}
		return jsScript;
	}


	private void saveArticlePropagate(Integer userid,
			String articleId ,  Integer originalUserid) {
		Article article = null;
		try {
			article = articleService.selectArticleById(articleId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		if(article != null){
			ArticlePropagate pro = articlePropagateDao.selectByUserIdAndArticleId(userid,articleId);
			if(pro == null){
				pro = new ArticlePropagate();
				pro.setUserid(userid);
				pro.setArticleId(articleId);
				pro.setStatus("OK#");
				pro.setArticleName(article.getTitle());
				pro.setParentId(originalUserid);
				pro.setInsertTime(new Date());
				articlePropagateDao.insert(pro);
			}
		}
		
	}
	
	
	@Override
	public String saveShareArticleRecord(ShareArticle vo) throws BusinessException {
		String jsScript = loadJS();  
		Integer userid = vo.getUserid();
		String articleId = vo.getArticleId();
		Integer originalUserId = vo.getOriginalUserid();
		if(userid != null&&vo.getArticleId()!=null
				&&vo.getOriginalUserid()!=null){
			try{
				saveArticlePropagate(userid, articleId,originalUserId);
				vo.setInsertTime(new Date());
				shareArticleDao.insert(vo);
			}catch(DataAccessException e){
				e.printStackTrace();
				log.debug("保存文章分享记录。");
			}
		}
		return jsScript;
	}
	
	
	private String loadJS() throws BusinessException {
		List<String> lines = null;
		String filePath = "C:\\js\\userrecord.js";
		try {
		    File file=new File(filePath);
		    //判断文件是否存在
            if(!file.isFile() || !file.exists()){ 
            	log.debug("找不到指定的文件。");
            	throw  new BusinessException("找不到指定的文件。");
            }
			lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("读取文件失败："+e.getMessage());
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
			 jsScript = loadJS();  
			 
			 WxUser isExistUser = null;
			 if(StringUtils.isNotBlank(wxUser.getOpenid())){
				 //根据openid查询用户信息
				 isExistUser = wxUserService.selectUserByOpendId(wxUser.getOpenid());
			 }
			 if(isExistUser == null){
				 wxUser.setInsertTime(new Date());
				 wxUser.setUpdateTime(new Date());
				 //保存微信用户信息
				 wxUserService.addWxUserInfo(wxUser);
			 }else{
				 wxUser.setUserid(isExistUser.getUserid());
				 wxUser.setUpdateTime(new Date());
				 //更新微信用户信息
				 wxUserService.updateByPrimaryKey(wxUser);
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
