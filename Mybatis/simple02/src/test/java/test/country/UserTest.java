package test.country;

import com.blueice.mapper.SysUserMapper;
import com.blueice.model.SysPrivilege;
import com.blueice.model.SysRole;
import com.blueice.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseMapperTest;

import java.util.List;

public class UserTest extends BaseMapperTest {


    /**
     * 多表关联查询（1对1）的测试
     */
    @Test
    public void selectUserAndRoleById() {
        SqlSession sqlSession = getSqlsession();
        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
//            SysUser user = mapper.selectUserAndRoleById(1L);
            SysUser user = mapper.selectUserAndRoleById2(1L);
            System.out.println("user:"+user.toString());
            Assert.assertTrue(user != null);

        } finally {
            sqlSession.close();
        }
    }

    /**
     * 多表关联查询（1对1）的测试
     * association 嵌套查询
     */
    @Test
    public void userRoleMapSelect() {
        SqlSession sqlSession = getSqlsession();
        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = mapper.userRoleMapSelect(1001L);
            System.out.println("user:"+user.toString());
            Assert.assertTrue(user != null);

        } finally {
            sqlSession.close();
        }
    }

    /**
     多表关联查询（1对1）的测试
     * association 嵌套查询之懒加载
     */
    @Test
    public void selectUserAndRoleByIdLazyLoad() {
        SqlSession sqlSession = getSqlsession();
        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = mapper.userRoleMapSelectLazy(1001L);
            Assert.assertTrue(user != null);
            System.out.println("调用 user.getRole()");
            Assert.assertNotNull(user.getRole());//只有在调用getRole()方法里，才会去执行第二条sql查询
            System.out.println("user:"+user.toString());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 一对多查询测试
     */
    @Test
    public void selectAllUserAndRole() {
        SqlSession sqlSession = getSqlsession();
        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> userList = mapper.selectAllUserAndRole();
            System.out.println("userListSize:"+userList.size());
            for(SysUser user:userList){
                System.out.println("姓名:"+user.getUserName());
                for(SysRole role:user.getRoles()){
                    System.out.println("  角色:"+role.getRoleName());
                }
                System.out.println("===========================");
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 一对多两层嵌套查询测试
     */
    @Test
    public void selectAllUserAndRoleAndPrivilage() {
        SqlSession sqlSession = getSqlsession();
        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> userList = mapper.selectAllUserAndRoleAndPri();
            System.out.println("userListSize:"+userList.size());
            for(SysUser user:userList){
                System.out.println("姓名:"+user.getUserName());
                for(SysRole role:user.getRoles()){
                    System.out.println("  角色:"+role.getRoleName());
                    for(SysPrivilege priv:role.getPrivilegeList()){
                        System.out.println("    权限:"+priv.getPrivilegeName());
                        System.out.println("    权限URL:"+priv.getPrivilegeUrl());
                    }
                }
                System.out.println("===========================");
            }
        } finally {
            sqlSession.close();
        }
    }


}
