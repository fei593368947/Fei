package com.zs.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zs.pms.dao.DepDao;
import com.zs.pms.po.TDep;

public interface DepService {

	//根据上级id获取下级部门列表
	public List<TDep> queryByPid(int pid);
	
}
