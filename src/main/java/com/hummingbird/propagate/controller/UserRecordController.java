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
import com.hummingbird.commonbiz.exception.TokenException;
import com.hummingbird.commonbiz.vo.BaseTransVO;
import com.hummingbird.propagate.entity.Token;
import com.hummingbird.propagate.services.TokenService;
import com.hummingbird.propagate.services.UserRecordService;
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

			//返回对应的 js的内容
			String result = "";
			rm.put("result", result);
			
		} catch (Exception e1) {
			log.error(String.format("%s失败",rm.getBaseMsg()), e1);
			rm.mergeException(e1);
		} 
		return rm;
	}
	
}
