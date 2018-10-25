package test.UserTest;

import com.blueice.mapper.SysUserMapper;
import com.blueice.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseMapperTest;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectUserById(){
        SqlSession sqlSession = getSqlsession();

        try {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            mapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());
            System.out.println("Name:"+user.getUserName());

        }finally {
            sqlSession.close();
        }
    }

}
