package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserService {

	//测试方法
	public void hello();
	//通过id查询用户权限列表
	public List<TPermission> queryByUid(int id);
	
	//根据原有权限整理菜单
	public List<TPermission> getMenu(List<TPermission> pers);
	
	//根据条件查询
	public List<TUser> queryByCon(QueryUser qu);
	
	//根据用户名查询
	public TUser queryById(int id);
	
	//批量删除
	public void deleteUserByIds(int [] ids);
	
	//删除一条
	public void deleteUserById(int id);
	
	//修改
	public void updateUser(TUser tu);
	
	//新增
	public int insertUser(TUser tu) throws AppException;
	
	//分页查询
	public List<TUser> queryByPage(int page,QueryUser qu);
	
	//查询总页数
	public int queryPageCont(QueryUser qu);
	
}
