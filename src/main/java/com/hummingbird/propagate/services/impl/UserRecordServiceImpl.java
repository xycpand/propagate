package com.hummingbird.propagate.services.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllBytes;  
import static java.nio.file.Paths.get;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.commonbiz.exception.TokenException;
import com.hummingbird.propagate.entity.AskByJS;
import com.hummingbird.propagate.entity.Token;
import com.hummingbird.propagate.entity.UserRecord;
import com.hummingbird.propagate.mapper.UserRecordMapper;
import com.hummingbird.propagate.services.ArticleService;
import com.hummingbird.propagate.services.TokenService;
import com.hummingbird.propagate.services.UserRecordService;
import com.hummingbird.propagate.services.WxUserService;
import com.hummingbird.propagate.vo.SaveUserRecordVO;

@Service
public class UserRecordServiceImpl implements UserRecordService{

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());

	@Autowired
	TokenService tokenSrv;
	@Autowired
	UserRecordMapper userRecordDao;
	@Autowired
	WxUserService wxUserService;
	@Autowired
	ArticleService articleService;

	@Override
	public String askByJS(AskByJS vo) throws BusinessException {
		String jsScript = "";
		List<String> lines = null;
		String filePath = "C:\\js\\jquery-1.11.1.js";
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
         jsScript = sb.toString();  
	    try{      
	       //保存用户浏览记录
			UserRecord userRecord = new UserRecord();
			userRecord.setReaderId(vo.getX_reader());
			userRecord.setSharerId(vo.getX_sharer());
			userRecord.setContentId(vo.getX_content());
			userRecord.setInsertTime(new Date());
			userRecord.setUpdateTime(new Date());
			userRecordDao.insert(userRecord);
		}catch(DataAccessException e){
			e.printStackTrace();
			log.debug("保存用户浏览记录失败。");
		}
		return jsScript;
	}
	

	@Override
	public void saveUserRecord(String appId, SaveUserRecordVO vo) throws BusinessException {
		try{
			//根据token获取openid
			String openid = wxUserService.getOpenidByUserId(vo.getToken(), appId);
			
			UserRecord userRecord = new UserRecord();
			userRecord.setReaderId(openid);
			userRecord.setSharerId(vo.getX_sharer());
			userRecord.setContentId(vo.getX_content());
			userRecord.setInsertTime(new Date());
			userRecord.setUpdateTime(new Date());
			//保存用户浏览记录
			userRecordDao.insert(userRecord);
			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new BusinessException("保存用户浏览记录失败。");
		}
	}

	  public static void main(String[] args)throws Exception{  
		  String fromFile = "";
		  // Java7 : Files.readAllBytes默认以UTF-8编码读入文件，故文件的编码如果不是UTF-8，那么中文内容会出现乱字符  
		  try {
			fromFile = new String(Files.readAllBytes(Paths.get("C:\\js\\jquery-1.11.1.js")));
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("java7读取文件失败。");
		}  
          System.out.println("Java7默认以UTF-8编码读入文件，故文件的编码如果不是UTF-8，那么中文内容会出现乱字符  :");   
		  System.out.println(fromFile);  
		  
	      // Java8用流的方式读文件，更加高效
	      List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get("C:\\js\\jquery-1.11.1.js"), StandardCharsets.UTF_8);
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
