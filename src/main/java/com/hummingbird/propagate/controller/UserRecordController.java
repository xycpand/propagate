package com.hummingbird.propagate.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hummingbird.common.controller.BaseController;
import com.hummingbird.propagate.entity.ReadArticle;
import com.hummingbird.propagate.entity.ShareArticle;
import com.hummingbird.propagate.entity.WxUser;
import com.hummingbird.propagate.services.UserRecordService;
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
	 * http://ip:port/propagate/userRecord/userrecord.js?x_reader=xxxxxxx&x_content=xxxxxxx
	 * @return
	 */
	@RequestMapping(value = "/userrecord.js")
	public void  saveReadArticleRecord(HttpServletRequest request,
			HttpServletResponse response,ReadArticle vo) {
		try {
			String jsContent = userRecordService.saveReadArticleRecord(vo);
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
	 * http://ip:port/propagate/userRecord/userappend.js?x_reader=xxxxxxx&x_sharer=xxxxxxx&x_content=xxxxxxx
	 * http://localhost:6060/propagate/userRecord/userappend.js?x_reader=op3Eiwm0MUHS3BfHiip58JF5lb-&x_sharer=op3EiwkPYRENALJj3gR3Q98ehW1Q&x_content=abc
	 * @return
	 */
	@RequestMapping(value = "/userappend.js")
	public void  userappend(HttpServletRequest request,
			HttpServletResponse response,ShareArticle vo) {
		try {
			String jsContent = userRecordService.saveShareArticleRecord(vo);
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
	 * http://ip:port/propagate/userRecord/userinfo.js?openid=xxxx&nickname=xxxx
	 * &sex=xxxx&province=xxxx&city=xxxx&country=xxxx&headimgurl=xxxx&privilege=xxxx
	 * &unionid=xxxx&x_content=xxxx
	 * @return
	 */
	@RequestMapping(value = "/userinfo.js")
	public void saveUserInfo(HttpServletRequest request,
			HttpServletResponse response,WxUser wxUser) {
		try {
			String jsContent = userRecordService.saveUserInfo(wxUser);
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
	 * 跳转到文章内容页
	 * @param mav
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "article.html")
	public ModelAndView toArticle(ModelAndView mav,HttpServletRequest request) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorInfo", "系统异常，请稍候再试");
		}
		mav.setViewName("article");
		return mav;
	}
	
}
