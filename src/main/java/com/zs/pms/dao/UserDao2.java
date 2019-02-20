package com.zs.pms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;
@Repository  //数据操作
public interface UserDao2 {

	@Select("select * from tuser where sex=#{sex}")
	public List<TUser> queryByCon(QueryUser qu);
	
}
