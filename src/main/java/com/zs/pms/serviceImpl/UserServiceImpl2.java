package com.zs.pms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.pms.dao.UserDao2;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService2;
import com.zs.pms.vo.QueryUser;
@Service
public class UserServiceImpl2 implements UserService2 {

	@Override
	public void addUser(TUser user) {
		// TODO Auto-generated method stub
		System.out.println("新增用户。。。。。。");
	}

	@Override
	public void updateUser(TUser user) {
		// TODO Auto-generated method stub
		System.out.println("修改用户。。。。。。");
	}

	

}
