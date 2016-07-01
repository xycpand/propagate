package com.hummingbird.propagate.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hummingbird.common.controller.BaseController;
import com.hummingbird.common.ext.AccessRequered;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.commonbiz.vo.BaseTransVO;
import com.hummingbird.propagate.services.UserRecordService;
import com.hummingbird.propagate.vo.SaveArticleVO;
import com.hummingbird.propagate.vo.SaveReadArticleVO;
import com.hummingbird.propagate.vo.SaveShareArticleVO;
import com.hummingbird.propagate.vo.SaveShareRecordVO;
import com.hummingbird.propagate.vo.UserVO;
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
		 *保存文章信息
		 * @return
		 */
		@RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
		@AccessRequered(methodName = "保存文章信息", isJson = true, codebase = 245900, className = "com.hummingbird.commonbiz.vo.BaseTransVO", genericClassName = "com.hummingbird.propagate.vo.SaveArticleVO", appLog = true)
		public @ResponseBody ResultModel saveArticle(HttpServletRequest request,
				HttpServletResponse response) {
		ResultModel rm = super.getResultModel();
	    BaseTransVO<SaveArticleVO> transorder = (BaseTransVO<SaveArticleVO>) super.getParameterObject();
		String messagebase = "保存文章信息";
		try {
			userRecordService.saveArticle(transorder.getBody());
			rm.setErrmsg(messagebase+"成功");
		} catch (Exception e1) {
			log.error(String.format(messagebase + "失败"), e1);
			rm.mergeException(e1);
		} 
		return rm;
	}
		

		/**
		 *通过js方式保存文章信息
		 *加载save_article.js
		 * @return
		 */
		@RequestMapping(value = "/save_article.js")
		public void  saveArticleByJs(HttpServletRequest request,
				HttpServletResponse response,SaveArticleVO vo) {
			try {
				userRecordService.saveArticle(vo);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} 
		}
	

		/**
		 *保存分享记录
		 * @return
		 */
		@RequestMapping(value = "/saveShareRecord", method = RequestMethod.POST)
		@AccessRequered(methodName = "保存分享记录", isJson = true, codebase = 245900, className = "com.hummingbird.commonbiz.vo.BaseTransVO", genericClassName = "com.hummingbird.propagate.vo.SaveShareRecordVO", appLog = true)
		public @ResponseBody ResultModel saveShareRecord(HttpServletRequest request,
				HttpServletResponse response) {
		ResultModel rm = super.getResultModel();
	    BaseTransVO<SaveShareRecordVO> transorder = (BaseTransVO<SaveShareRecordVO>) super.getParameterObject();
		String messagebase = "保存分享记录";
		try {
			userRecordService.saveShareRecord(transorder.getBody());
			rm.setErrmsg(messagebase+"成功");
		} catch (Exception e1) {
			log.error(String.format(messagebase + "失败"), e1);
			rm.mergeException(e1);
		} 
		return rm;
	}
		

		/**
		 *js方式保存分享记录
		 * @return
		 */
		@RequestMapping(value = "/save_share.js")
		public void  saveShareRecordByJS(HttpServletRequest request,
				HttpServletResponse response,SaveShareRecordVO vo) {
			try {
				userRecordService.saveShareRecord(vo);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			} 
		}
		

	/**
	 *加载userread.js
	 * @return
	 */
	@RequestMapping(value = "/userread.js")
	public void  saveReadArticleRecord(HttpServletRequest request,
			HttpServletResponse response,SaveReadArticleVO vo) {
		try {
			userRecordService.saveReadArticleRecord(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} 
	}
	
	

	/**
	 *加载propagate.js
	 * @return
	 */
	@RequestMapping(value = "/propagate.js")
	public void  loadPropagateJS(HttpServletRequest request,
			HttpServletResponse response,String x_articleId) {
		try {
			String jsContent = userRecordService.loadPropagateJS(x_articleId,request);
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
	 * 前端js判断url中是否有originalUserid参数，如果存在则需要通过该方法加载userappend.js
	 * @return
	 */
	@RequestMapping(value = "/userappend.js")
	public void  userappend(HttpServletRequest request,
			HttpServletResponse response,SaveShareArticleVO vo) {
		try {
			userRecordService.saveShareArticleRecord(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} 
	}
	
	
	/**  
	 * 上报微信消息
	 * http://ip:port/propagate/userRecord/userinfo.js?openid=xxxx&nickname=xxxx
	 * @return
	 */
	@RequestMapping(value = "/userinfo.js")
	public void saveUserInfo(ModelAndView mav,HttpServletRequest request,
			HttpServletResponse response,UserVO userVO) {
		try {
			userRecordService.saveUserInfo(userVO);
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
		String basePath = "";
		try {
			 basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorInfo", "系统异常，请稍候再试");
		}
		mav.addObject("basePath", basePath);
		mav.setViewName("article");
		return mav;
	}
	
	/**
	 * 跳转到测试js的页面
	 * @param mav
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "testJS.html")
	public ModelAndView testJS(ModelAndView mav,HttpServletRequest request) {
		String basePath = "";
		try {
			 basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorInfo", "系统异常，请稍候再试");
		}
		mav.addObject("basePath", basePath);
		mav.setViewName("testJS");
		return mav;
	}
	
}
