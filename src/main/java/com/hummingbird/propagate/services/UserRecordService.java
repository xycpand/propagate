package com.hummingbird.propagate.services;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.propagate.vo.SaveUserRecordVO;

/**
 * @author panda
 * 2016年6月15日 下午2:59:01
 * 本类主要做为 用户浏览记录service
 */
public interface UserRecordService {

	/**
	 * 保存用户浏览记录
	 * @param appId
	 * @param vo
	 * @throws BusinessException
	 */
	public void saveUserRecord(String appId,SaveUserRecordVO vo) throws BusinessException;
	

}
