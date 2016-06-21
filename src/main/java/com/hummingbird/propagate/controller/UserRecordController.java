package com.hummingbird.propagate.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hummingbird.common.controller.BaseController;
import com.hummingbird.common.vo.ResultModel;
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
	 *加载userread.js
	 * @return
	 */
	@RequestMapping(value = "/userread.js")
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
	 * 前端js判断url中是否有originalUserid参数，如果存在则需要通过该方法加载userappend.js
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
	 *保存分享记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveShareArticleRecord")
	public @ResponseBody ResultModel queryMyObjectTenderSurvey(HttpServletRequest request,
			HttpServletResponse response,ShareArticle vo) {
		ResultModel rm = super.getResultModel();
		String messagebase = "保存分享记录";
		try {
			userRecordService.saveShareArticleRecord(vo);
			rm.setErrmsg(messagebase+"成功");
		} catch (Exception e1) {
			log.error(String.format(messagebase + "失败"), e1);
			rm.mergeException(e1);
		} 
		return rm;

	}
	

	/**  
	 * 上报微信消息
	 * http://ip:port/propagate/userRecord/userinfo.js?openid=xxxx&nickname=xxxx
	 * @return
	 */
	@RequestMapping(value = "/userinfo.js")
	public ModelAndView saveUserInfo(ModelAndView mav,HttpServletRequest request,
			HttpServletResponse response,WxUser wxUser) {
		try {
			String jsContent = userRecordService.saveUserInfo(wxUser);
            response.setContentType("text/html;charset=UTF-8"); 
			response.getWriter().write(jsContent);
			response.getWriter().flush();
			response.getWriter().close();
			throw new Exception("测试异常信息。");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("errorInfo", e.getMessage());
		} 
		return mav;
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
	
	private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
