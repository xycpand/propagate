package com.hummingbird.propagate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hummingbird.common.controller.BaseController;
import com.hummingbird.common.event.EventListenerContainer;
import com.hummingbird.common.event.RequestEvent;
import com.hummingbird.common.ext.AccessRequered;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.commonbiz.exception.TokenException;
import com.hummingbird.commonbiz.vo.BaseTransVO;
import com.hummingbird.propagate.services.UserTagService;
import com.hummingbird.propagate.vo.AddArticleTagBodyVO;
import com.hummingbird.propagate.vo.ArticleTagBodyVO;
import com.hummingbird.propagate.vo.OpenidBodyVO;
import com.hummingbird.propagate.vo.QueryUserTagReruenVO;

@Controller
@RequestMapping(value="/userTag")
public class UserTagController extends BaseController{
	@Autowired
	UserTagService userTagService;
	//1. 查询用户的标签
	@RequestMapping(value = "/queryUserTag", method = RequestMethod.POST)
	@AccessRequered(methodName = "查询用户的标签接口", isJson = true, codebase = 245900, className = "com.hummingbird.commonbiz.vo.BaseTransVO", genericClassName = "com.hummingbird.paas.vo.OpenidBodyVO", appLog = true)
	public @ResponseBody ResultModel queryUserTag(HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = super.getResultModel();
		BaseTransVO<OpenidBodyVO> transorder = (BaseTransVO<OpenidBodyVO>) super.getParameterObject();
		String messagebase = "查询用户的标签";

		RequestEvent qe = null; // 业务请求事件,当实现一些关键的业务时,需要生成该请求

		try {
			// 业务数据必填等校验
			// 业务数据逻辑校验
			if (log.isDebugEnabled()) {
				log.debug("检验通过，获取请求");
			}
			if(StringUtils.isBlank(transorder.getBody().getOpenid())){
				log.error(String.format("openid不能为空",transorder.getBody().getOpenid()));
				throw new TokenException("openid不能为空");
			}
			QueryUserTagReruenVO result = userTagService.queryUserTag(transorder.getBody().getOpenid());
			rm.put("result",result);
			rm.setErrmsg(messagebase+"成功");
		} catch (Exception e1) {
			log.error(String.format(messagebase + "失败"), e1);
			rm.mergeException(e1);
			if (qe != null)
				qe.setSuccessed(false);
		} 
		return rm;
	}
	
	
	
	//2. 查询某文章的标签
	@RequestMapping(value = "/queryArticleTag", method = RequestMethod.POST)
	@AccessRequered(methodName = "查询某文章的标签", isJson = true, codebase = 245901, className = "com.hummingbird.commonbiz.vo.BaseTransVO", genericClassName = "com.hummingbird.paas.vo.ArticleTagBodyVO", appLog = true)
	public @ResponseBody ResultModel queryArticleTag(HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = super.getResultModel();
		BaseTransVO<ArticleTagBodyVO> transorder = (BaseTransVO<ArticleTagBodyVO>) super.getParameterObject();
		String messagebase = "查询某文章的标签";

		RequestEvent qe = null; // 业务请求事件,当实现一些关键的业务时,需要生成该请求

		try {
			// 业务数据必填等校验
			// 业务数据逻辑校验
			if (log.isDebugEnabled()) {
				log.debug("检验通过，获取请求");
			}
			if(StringUtils.isBlank(transorder.getBody().getOpenid())){
				log.error(String.format("openid不能为空",transorder.getBody().getOpenid()));
				throw new TokenException("openid不能为空");
			}
			if(StringUtils.isBlank(transorder.getBody().getArticleId())){
				log.error(String.format("articleid不能为空",transorder.getBody().getArticleId()));
				throw new TokenException("articleid不能为空");
			}
			QueryUserTagReruenVO result = userTagService.queryArticleTag(transorder.getBody().getArticleId());
			rm.put("result",result);
			rm.setErrmsg(messagebase+"成功");
		} catch (Exception e1) {
			log.error(String.format(messagebase + "失败"), e1);
			rm.mergeException(e1);
			if (qe != null)
				qe.setSuccessed(false);
		} 
		return rm;
	}
	
	//3. 为用户的某篇文章添加标签
	@RequestMapping(value = "/addArticleTag", method = RequestMethod.POST)
	@AccessRequered(methodName = "为用户的某篇文章添加标签", isJson = true, codebase = 245902, className = "com.hummingbird.commonbiz.vo.BaseTransVO", genericClassName = "com.hummingbird.paas.vo.AddArticleTagBodyVO", appLog = true)
	public @ResponseBody ResultModel addArticleTag(HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = super.getResultModel();
		BaseTransVO<AddArticleTagBodyVO> transorder = (BaseTransVO<AddArticleTagBodyVO>) super.getParameterObject();
		String messagebase = "为用户的某篇文章添加标签";

		RequestEvent qe = null; // 业务请求事件,当实现一些关键的业务时,需要生成该请求

		try {
			// 业务数据必填等校验
			// 业务数据逻辑校验
			if (log.isDebugEnabled()) {
				log.debug("检验通过，获取请求");
			}
			if(StringUtils.isBlank(transorder.getBody().getOpenid())){
				log.error(String.format("openid不能为空",transorder.getBody().getOpenid()));
				throw new TokenException("openid不能为空");
			}
			if(StringUtils.isBlank(transorder.getBody().getArticleId())){
				log.error(String.format("articleid不能为空",transorder.getBody().getArticleId()));
				throw new TokenException("articleid不能为空");
			}
			//添加标签
			userTagService.addArticleTag(transorder.getBody());
			rm.setErrmsg(messagebase+"成功");
		} catch (Exception e1) {
			log.error(String.format(messagebase + "失败"), e1);
			rm.mergeException(e1);
			if (qe != null)
				qe.setSuccessed(false);
		} 
		return rm;
	}
	
	//4. 为用户的某篇文章删除标签
	
	
	//5. 查询热门标签
	@RequestMapping(value = "/queryHotTag", method = RequestMethod.POST)
	@AccessRequered(methodName = "查询热门标签", isJson = true, codebase = 245905, className = "com.hummingbird.commonbiz.vo.BaseTransVO", genericClassName = "com.hummingbird.paas.vo.OpenidBodyVO", appLog = true)
	public @ResponseBody ResultModel queryHotTag(HttpServletRequest request,
			HttpServletResponse response) {
		ResultModel rm = super.getResultModel();
		BaseTransVO<OpenidBodyVO> transorder = (BaseTransVO<OpenidBodyVO>) super.getParameterObject();
		String messagebase = "查询热门标签";

		RequestEvent qe = null; // 业务请求事件,当实现一些关键的业务时,需要生成该请求

		try {
			if(StringUtils.isBlank(transorder.getBody().getOpenid())){
				log.error(String.format("openid不能为空",transorder.getBody().getOpenid()));
				throw new TokenException("openid不能为空");
			}
			QueryUserTagReruenVO result = userTagService.queryHotTag();
			rm.put("result",result);
			rm.setErrmsg(messagebase+"成功");
		} catch (Exception e1) {
			log.error(String.format(messagebase + "失败"), e1);
			rm.mergeException(e1);
			if (qe != null)
				qe.setSuccessed(false);
		} 
		return rm;
	}
	
}
