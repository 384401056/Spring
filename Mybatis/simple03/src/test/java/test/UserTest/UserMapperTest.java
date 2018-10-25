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
     * SysRole设置了二级缓存后的测试.注意：设置可读可写的二级缓存,Java对象要进行序列化。
     * (一种是mybatis自带的二级缓存，一种是mybatis-redis框架，注意在SysRoleMapper.xml中修改后再看运行效果)
     * 当使用mybatis-redis时，无论后续运行多少次程序，都是从缓存中读取.
     * 日志中的Cache Hit Ratio就是缓存命中率。.0
     */
    @Test
    public void testCach2(){
        SysRole role1 = null;
        SqlSession sqlSession = getSqlsession();
        try {
            SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
            role1 = mapper.selectByPrimaryKey(1L);
            role1.setRoleName("New Name");
            SysRole role2 = mapper.selectByPrimaryKey(1L); //此时触发一级缓存,一级缓存的作用只在SqlSession的生命周期内。
            Assert.assertEquals("New Name", role2.getRoleName());

        } finally {
            sqlSession.close(); //当sqlsession关闭时，查询数据才会保存到二级缓存中。
        }

        System.out.println("开启新的Sqlsession");

        sqlSession = getSqlsession();
        try {
            SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole role2 = mapper.selectByPrimaryKey(1L);//触发二级缓存
            Assert.assertEquals("New Name", role2.getRoleName());
            Assert.assertNotEquals(role1, role2);
            SysRole role3 = mapper.selectByPrimaryKey(1L);//触发二级缓存
            Assert.assertNotEquals(role2, role3);
        } finally {
            sqlSession.close();
        }
    }

}
