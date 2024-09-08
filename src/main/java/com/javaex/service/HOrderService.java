package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.HOrderDao;
import com.javaex.vo.UserVo;

@Service
public class HOrderService {

	@Autowired
	private HOrderDao hOrderDao;
	
	public UserVo exeUserOne(UserVo userVo) {
		
		UserVo userOne = hOrderDao.getUserOne(userVo);
		
		return userOne;
	}
	
	
}
