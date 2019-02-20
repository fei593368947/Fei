package com.zs.pms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.po.TDep;
import com.zs.pms.service.DepService;

@Controller
public class DepController {

	@Autowired
	DepService ds;
	
	/**
	 * ajax获得二级部门
	 */
	@RequestMapping("/user/getdeps.do")
	/*
	 * 方法返回的对象或对象的集合
	 * 自动一json的形式写入Response中
	 */
	@ResponseBody
	public List<TDep> toAdd(int pid){
		return ds.queryByPid(pid);
	}
	
}
