package com.zs.pms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryUser;
@Service
@Transactional  //需要开启事物的业务对象
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	@Override
	public void hello() {
		// TODO Auto-generated method stub
		System.out.println("Hello Spring");
	}

	@Override
	public List<TPermission> queryByUid(int id) {
		// TODO Auto-generated method stub
		return dao.queryByUid(id);
	}

	@Override
	public List<TPermission> getMenu(List<TPermission> pers) {
		// TODO Auto-generated method stub
		//创建新容器
		List<TPermission> list = new ArrayList<>();
		//遍历集合列表
		for (TPermission per : pers) {
			//判断是否是一级菜单
			if (per.getLev()==1) {
				//加载该一级菜单下的二级菜单
				for (TPermission per1 : pers) {
					//二级菜单的pin等于一次菜单的id
					if (per1.getPid()==per.getId()) {
						//添加子权限集
						per.addChild(per1);
					}
				}
				list.add(per);
			}
		}
		return list;
	}
	
	/**
	 * 根据条件查询
	 */
	@Override
	public List<TUser> queryByCon(QueryUser qu) {
		// TODO Auto-generated method stub
		return dao.queryByCon(qu);
	}
	
	/**
	 * 根据用户名查询
	 */
	@Override
	public TUser queryById(int id) {
		// TODO Auto-generated method stub
		return dao.queryById(id);
	}
	
	/**
	 * 根据id批量删除
	 */
	@Override
	public void deleteUserByIds(int[] ids) {
		// TODO Auto-generated method stub
		dao.deleteUserByIds(ids);
	}
	
	/**
	 * 根据id删除用户
	 */
	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		dao.deleteUserById(id);
	}

	/**
	 * 修改用户
	 */
	@Override
	public void updateUser(TUser tu) {
		// TODO Auto-generated method stub
		dao.updateUser(tu);
	}

	/**
	 * 新增用户
	 * @throws AppException 
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int insertUser(TUser tu) throws AppException {
		// TODO Auto-generated method stub
		//模拟业务异常
		if ("admin".equals(tu.getLoginname())) {
			throw new AppException(1003, "登录名不能是admin");
		}
		//密码进行MD5加密
		MD5 md5 = new MD5();
		tu.setPassword(md5.getMD5ofStr(tu.getPassword()));
		//新增用户
		dao.insertUser(tu);
		
		return tu.getId();
	}

	/**
	 * 分页查询
	 */
	@Override
	public List<TUser> queryByPage(int page,QueryUser qu) {
		// TODO Auto-generated method stub
		//分页起始数
		int start = (page-1)*Constants.PAGECONT-1;
		//分页结束条数
		int end = page*Constants.PAGECONT;
		//将起始条数和结束条数封装
		qu.setStart(start);
		qu.setEnd(end);
		return dao.queryByPage(qu);
	}

	/**
	 * 根据总条数查询总页数
	 */
	@Override
	public int queryPageCont(QueryUser qu) {
		// TODO Auto-generated method stub
		//获得总条数
		int cont = dao.queryCount(qu);
		//判断总页数
		//如果整除就返回页数
		if (cont%Constants.PAGECONT==0) {
			return cont/Constants.PAGECONT;
		} else {
			//如果没整除页数+1
			return cont/Constants.PAGECONT+1;
		}
	}

	

}
