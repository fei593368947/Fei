import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.po.TDep;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class TestUserService {

	@Autowired
	UserService us;
	
	public void testHello() {
		us.hello();
	}
	
	
	public void testlogin() {
		List<TPermission> list1 = us.queryByUid(1001);
		for(TPermission per : us.queryByUid(1001)) {
			System.out.println(per.getPname());
		}
		System.out.println("================================");
		for(TPermission per1 : us.getMenu(list1)) {
			//一级权限
			System.out.println(per1.getPname());
			for(TPermission per2 : per1.getChildren()) {
				System.out.println("===="+per2.getPname());
			}
		}
	}
	
	@Test
	public void testQueryByCon() {
		QueryUser qu = new QueryUser();
		qu.setLoginname("lvbu");
		qu.setPassword("123");
		MD5 md5 = new MD5();
		List<TUser> list = us.queryByCon(qu);
		System.out.println(md5.getMD5ofStr(list.get(0).getPassword()));
	}
	
	
	public void testQuery() {
		//创建查询对象
		QueryUser qu = new QueryUser();
		qu.setSex("男");
		for(TUser user : us.queryByPage(1, qu)) {
			System.out.println(user.getId()+"\\"+user.getLoginname());
		}
		System.out.println("共"+us.queryPageCont(qu)+"页");
	}
	
	
	public void testQueryByLoginname() {
		int id = 1004;
		System.out.println(us.queryById(id));
	}
	
	
	public void testDeleteUserByIds() {
		int [] ids = {1007,1008};
		us.deleteUserByIds(ids);
	}
	
	
	public void testDeleteUserById() {
		int id = 1008;
		us.deleteUserById(id);
	}
	
	
	public void testUpdateUser() {
		TUser tu = new TUser();
		tu.setId(1006);
		tu.setBirthday(new Date());
		TDep dept = new TDep();
		dept.setId(6);
		tu.setDept(dept);
		tu.setEmail("update@163.com");
		tu.setIsenabled(1);
		tu.setLoginname("update");
		tu.setPassword("update123");
		tu.setPic("update.jsp");
		tu.setRealname("测试修改");
		tu.setSex("男");
		tu.setUpdator(1001);
		us.updateUser(tu);
	}
	
	
	public void testInsertUser() {
		TUser tu = new TUser();
		tu.setBirthday(new Date());
		TDep dept = new TDep();
		dept.setId(3);
		tu.setDept(dept);
		tu.setEmail("insert@163.com");
		tu.setIsenabled(1);
		tu.setLoginname("insert123");
		tu.setPassword("insert123");
		tu.setPic("insert.jsp");
		tu.setRealname("新增修改");
		tu.setSex("女");
		tu.setCreator(1001);
		System.out.println();
	}
	
}
