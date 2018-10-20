package test.UserTest;

import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseMapperTest;
import tk.mybatis.simple.mapper.UserMapper;
import tk.mybatis.simple.model.User;

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
}
