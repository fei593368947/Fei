package com.zs.pms.service;

import java.util.List;

import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;
/**
 * 真正的业务对象
 * @author 王鹏飞
 *
 */
public interface UserService2 {

	public void addUser(TUser user);
	
	public void updateUser(TUser user);
	
}
