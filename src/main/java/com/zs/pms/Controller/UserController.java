package com.zs.pms.Controller;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@Controller
public class UserController {

	@Autowired
	UserService us;
	@Autowired
	DepService ds;
	
	@RequestMapping("/user/list.do")
	//查询列表页
	public String list(QueryUser qu,String page,ModelMap model) {
		//判断如果当前页数为空直接赋值为1
		if (page==null) {
			page = "1";
		}
		//返回分页数据
		model.addAttribute("LIST", us.queryByPage(Integer.parseInt(page), qu));
		//返回当前总页数
		model.addAttribute("PAGECONT", us.queryPageCont(qu));
		//当前页
		model.addAttribute("PAGE", page);
		//条件
		model.addAttribute("QUERY", qu);
		return "user/list";
	}
	
	@RequestMapping("/user/toadd.do")
	public String toAdd(ModelMap model) {
		//返回一级部门
		model.addAttribute("DLIST", ds.queryByPid(0));
		return "user/add";
	}
	
	//新增用户
	@RequestMapping("/user/add.do")
	public String add(TUser tu,HttpSession session,ModelMap model) {
		//将session信息封装到TUser中
		TUser user = (TUser) session.getAttribute("CUSER");
		try {
			//装载数据
			//新增用户默认账号可用
			tu.setIsenabled(1);
			//创建人的id
			tu.setCreator(user.getId());
			//头像存储路径
			tu.setPic("");
			us.insertUser(tu);
			//跳转url
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			//将错误信息写入到页面
			model.addAttribute("MSG", e.getErrMsg());
			//调用方法传递参数
			return this.toAdd(model);
		}
	}
	
	//删除用户
	@RequestMapping("/user/delete.do")
	public String delete(int id) {
		//调用业务层删除用户的方法
		us.deleteUserById(id);
		//跳转URL
		return "redirect:list.do";
	}
	
	//批量删除
	@RequestMapping("/user/deletes.do")
	public String delete(int [] ids) {
		//调用业务层批量删除的方法
		us.deleteUserByIds(ids);
		//跳转URL
		return "redirect:list.do";
	}
	
	//根据id获得用户信息
	@RequestMapping("/user/get.do")
	public String get(int id,ModelMap model) {
		//返回用户信息
		model.addAttribute("USER", us.queryById(id));
		//返回一级部门
		model.addAttribute("DLIST", ds.queryByPid(0));
		//url跳转
		return "user/update";
	}
	
}
