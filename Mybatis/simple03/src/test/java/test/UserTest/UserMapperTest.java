package test.UserTest;

import com.blueice.mapper.SysRoleMapper;
import com.blueice.mapper.SysUserMapper;
import com.blueice.model.SysRole;
import com.blueice.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Maps;
import test.BaseMapperTest;

import java.util.List;
import java.util.Map;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = getSqlsession();

        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            mapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());
            System.out.println("Name:" + user.getUserName());
            System.out.println("User:" + user.toString());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserPage() {
        SqlSession sqlSession = getSqlsession();
        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            Map<String, Object> params = Maps.newHashMap();
            params.put("userName", "i");
            params.put("offset", 0);//从哪条记录开始.
            params.put("limit", 2);//每页共多少条.
            List<SysUser> userList = mapper.selectUserPage(params);
            Assert.assertNotNull(userList);

            for (SysUser user : userList) {
                System.out.println(user.toString());
            }
            System.out.println("总共多少条记录:" + params.get("total"));

        } finally {
            sqlSession.close();
        }
    }

    /**
     * 添加用户，并设置角色。
     */
    @Test
    public void testInsertUserAndRole() {
        SqlSession sqlSession = getSqlsession();
        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("Elex");
            user.setUserPassword("123456");
            user.setUserEmail("elex@163.com");
            user.setUserInfo("elex,elex,elex");
            user.setHeadImg(new byte[]{1, 2, 3});
            String roleIds = "1,2";
            int reslut = mapper.insertUserAndRole(user, roleIds);
            Assert.assertTrue(reslut > 0);
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * SysRole设置了二级缓存后的测试.
     */
    @Test
    public void testCach2(){
        SqlSession sqlSession = getSqlsession();
        try {
            SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole role1 = mapper.selectByPrimaryKey(1L);
            role1.setRoleName("New Name");
            SysRole role2 = mapper.selectByPrimaryKey(1L);
            Assert.assertEquals("New Name", role2.getRoleName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

}
