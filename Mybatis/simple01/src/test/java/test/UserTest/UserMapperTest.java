package test.UserTest;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseMapperTest;
import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.Role;
import tk.mybatis.simple.model.User;

import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectById(1L);
            System.out.printf("User:"+user);
            Assert.assertEquals("admin", user.getUserName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoles(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Role> roles = mapper.selectRolesByUserId(1L);
            System.out.printf("RolesSize:"+roles.size()+"\n");
            for(Role item: roles){
                System.out.printf(item.toString()+"\n");
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserName("Lili");
            user.setUserPassword("123456");
            user.setUserEmail("lili@163.com");
            user.setUserInfo("fdsafdksalfjdklsafj;d");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());

            int result = mapper.insert(user);

            Assert.assertEquals(1, result);

        }finally {
//            sqlSession.commit();//提交数据
            sqlSession.rollback();//为了不影响数据，这里直接回滚.
            sqlSession.close();
        }
    }


    @Test
    public void testInsertAndGetId(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserName("Jims");
            user.setUserPassword("123456");
            user.setUserEmail("Jims@163.com");
            user.setUserInfo("我是jims,哈哈哈...");
            user.setHeadImg(new byte[]{1,2,3,4,5,6,7,8});
            user.setCreateTime(new Date());
            int result = mapper.insertAndGetId(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
            System.out.printf("userId:" + user.getId());
        }finally {
//            sqlSession.commit();//提交数据
            sqlSession.rollback();//为了不影响数据，这里直接回滚.
            sqlSession.close();
        }
    }

    @Test
    public void testInsertAndGetId2(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserName("Jims");
            user.setUserPassword("123456");
            user.setUserEmail("Jims@163.com");
            user.setUserInfo("我是jims,哈哈哈...");
            user.setHeadImg(new byte[]{1,2,3,4,5,6,7,8});
            user.setCreateTime(new Date());
            int result = mapper.insertAndGetId2(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
            System.out.printf("userId:" + user.getId());
        }finally {
//            sqlSession.commit();//提交数据
            sqlSession.rollback();//为了不影响数据，这里直接回滚.
            sqlSession.close();
        }
    }

    @Test
    public void update(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectById(1037L); //从数据库查一条用户记录
            user.setUserName("高谚宾");
            user.setUserPassword("123456");
            user.setUserEmail("Gaoyanbin@163.com");
            user.setUserInfo("我是高谚宾,哈哈哈...");
            user.setHeadImg(new byte[]{1,2});
            user.setCreateTime(new Date());
            int result = mapper.update(user);
            Assert.assertEquals(1, result);
            Assert.assertEquals("高谚宾", user.getUserName());
            System.out.printf("User:"+user.toString());
        }finally {
            //sqlSession.commit();//提交数据
            sqlSession.rollback();//为了不影响数据，这里直接回滚.
            sqlSession.close();
        }
    }


    @Test
    public void delete(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectById(1037L); //从数据库查一条用户记录
            Assert.assertNotNull(user);
            int result = mapper.delete(1037L);//删除用户记录
            Assert.assertEquals(1, result);
            Assert.assertNull(mapper.selectById(1037L));
        }finally {
            //sqlSession.commit();//提交数据
            sqlSession.rollback();//为了不影响数据，这里直接回滚.
            sqlSession.close();
        }
    }

    @Test
    public void selectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<Role> roleList = mapper.selectRolesByUserIdAndRoleEnabled(1L,1);
            System.out.printf("roleListSize:"+roleList.size());
            Assert.assertTrue(roleList.size()>0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectByUserIdOrUserName() {
        SqlSession sqlSession = getSqlsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//            List<User> userList = mapper.selectByUserIdOrUserName(null, null);//无返回
//            List<User> userList = mapper.selectByUserIdOrUserName(null, "Jims");//只通过userName进行查询
//            List<User> userList = mapper.selectByUserIdOrUserName(1036L, "");//只通过userId进行查询
            List<User> userList = mapper.selectByUserIdOrUserName(1L, "admin");//userId and userName

        } finally {
            sqlSession.close();
        }
    }

}
