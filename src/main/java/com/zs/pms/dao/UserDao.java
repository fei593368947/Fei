package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserDao {

	//根据用户id获得权限列表
	public List<TPermission> queryByUid(int id);
	
	//根据条件查询
	public List<TUser> queryByCon(QueryUser qu);
	
	//根据主键读取
	public TUser queryById(int id);
	
	//批量删除
	public void deleteUserByIds(int [] ids);
	
	//删除一条
	public void deleteUserById(int id);
	
	//修改
	public void updateUser(TUser tu);
	
	//新增
	public int insertUser(TUser tu);
	
	//分页查询
	public List<TUser> queryByPage(QueryUser qu);
	
	//查询总条数
	public int queryCount(QueryUser qu);
	
}
