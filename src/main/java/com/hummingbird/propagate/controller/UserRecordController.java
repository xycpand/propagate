package com.hummingbird.propagate.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hummingbird.common.controller.BaseController;
import com.hummingbird.common.ext.AccessRequered;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.commonbiz.vo.BaseTransVO;
import com.hummingbird.propagate.entity.AskByJS;
import com.hummingbird.propagate.services.UserRecordService;
import com.hummingbird.propagate.vo.SaveUserInfoVO;
import com.hummingbird.propagate.vo.SaveUserRecordVO;
/**
 * @author panda
 * 2016年6月15日 下午2:59:01
 * 本类主要做为 保存用户浏览记录
 */
@Controller
@RequestMapping(value="/userRecord"	 )
public class UserRecordController extends BaseController  {
	@Autowired
	UserRecordService userRecordService;


	/**
	 *js方式请求数据
	 * http://ip:port/propagate/userrecord.js?x_reader=xxxxxxx&x_content=xxxxxxx
	 * @return
	 */
	@RequestMapping(value = "/userrecord.js")
	public void  askByJS(HttpServletRequest request,
			HttpServletResponse response,AskByJS vo) {
		try {
			String jsContent = userRecordService.askByJS(vo);
            response.setContentType("text/html;charset=UTF-8"); 
			response.getWriter().write(jsContent);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} 
	}
	

	/**
	 * 前端js判断url中是否有x_sharer参数，如果存在则需要通过该方法加载另一段
	 * http://ip:port/propagate/userappend.js?x_reader=xxxxxxx&x_sharer=xxxxxxx&x_content=xxxxxxx
	 * @return
	 */
	@RequestMapping(value = "/userappend.js")
	public void  userappend(HttpServletRequest request,
			HttpServletResponse response,AskByJS vo) {
		try {
			String jsContent = userRecordService.askByJS(vo);
            response.setContentType("text/html;charset=UTF-8"); 
			response.getWriter().write(jsContent);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} 
	}
	

	/**  
	 * 上报微信消息
	 * http://ip:port/propagate/userinfo.js?openid=xxxx&nickname=xxxx
	 * &sex=xxxx&province=xxxx&city=xxxx&country=xxxx&headimgurl=xxxx&privilege=xxxx
	 * &unionid=xxxx&x_content=xxxx
	 * @return
	 */
	@RequestMapping(value = "/userinfo.js")
	public void  saveUserInfo(HttpServletRequest request,
			HttpServletResponse response,SaveUserInfoVO vo) {
		try {
			String jsContent = userRecordService.saveUserInfo(vo);
            response.setContentType("text/html;charset=UTF-8"); 
			response.getWriter().write(jsContent);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} 
	}
	
	/**
	 *保存用户浏览记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveUserRecord", method = RequestMethod.POST)
	@AccessRequered(methodName = "保存用户浏览记录", isJson = true, codebase = 252800 , className = "com.hummingbird.commonbiz.vo.BaseTransVO", genericClassName = "com.hummingbird.propagate.vo.SaveUserRecordVO", appLog = true)
	public @ResponseBody ResultModel saveUserRecord(HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = super.getResultModel();
		BaseTransVO<SaveUserRecordVO> transorder = (BaseTransVO<SaveUserRecordVO>) super.getParameterObject();
		try {
			
			userRecordService.saveUserRecord(transorder.getApp().getAppId(),transorder.getBody());

		} catch (Exception e1) {
			rm.mergeException(e1);
		} 
		return rm;
	}
	
}
