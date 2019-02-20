package com.zs.pms.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.DateUtil;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryLogin;
import com.zs.pms.vo.QueryUser;

@Controller  //是一个控制器
public class LoginController {
	
	@Autowired
	UserService us;

	@RequestMapping("/tologin.do")
	//去登陆页面
	public String tologin() {
		return "login";
	}
	
	@RequestMapping("/login.do")
	//去主页面
	public String login(QueryLogin login,ModelMap model,HttpSession session,QueryUser user) {
		//获得一开始生成的验证码信息
		String chkcode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		//判断页面生成的验证码和一开始生成的不一样,返回登陆页面
		if (!chkcode.equals(login.getChkcode())) {
			model.addAttribute("MSG", "验证码输入有误");
			return "login";
		}
		user.setLoginname(login.getUsername());
		//MD5加密
		MD5 md5 = new MD5();
		user.setPassword(md5.getMD5ofStr(login.getPassword()));
		user.setIsenabled(1);
		//获得用户信息列表
		List<TUser> users = us.queryByCon(user);
		//判断用户信息是否为空获得不为1
		if (users==null||users.size()!=1) {
			model.addAttribute("MSG", "账号密码输入有误");
			return "login";
		}
		//登陆成功后将信息封装到session
		session.setAttribute("CUSER", users.get(0));
		return "main";  //去主页面
	}
	
	@RequestMapping("/top.do")
	//去上边页面
	public String top(ModelMap model) {
		model.addAttribute("DAY", DateUtil.getStrDate());
		model.addAttribute("TODAY", DateUtil.getDateToStr(new Date()));
		return "top";
	}
	
	@RequestMapping("/left.do")
	//去左侧页面
	public String left(HttpSession session,ModelMap model) {
		//获得当前登陆用户
		TUser user = (TUser) session.getAttribute("CUSER");
		//获得该用户的权限列表
		List<TPermission> list1 = us.queryByUid(user.getId());
		//返回菜单
		model.addAttribute("MENU", us.getMenu(list1));
		return "left";
	}
	
	@RequestMapping("/right")
	//去右侧页面
	public String main() {
		return "right";
	}
	
}
