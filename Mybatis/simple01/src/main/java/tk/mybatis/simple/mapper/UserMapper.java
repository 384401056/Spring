package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.Role;
import tk.mybatis.simple.model.User;

import java.util.List;

public interface UserMapper {

    User selectById(Long id);

    List<User> selectAll();

    List<Role> selectRolesByUserId(@Param("userId") Long userId);

    int insert(User user);

    int insertAndGetId(User user);

    //通过selectkey来获取插入数据的id
    int insertAndGetId2(User user);

    //插入数据
    int update(User user);

    //删除用户
    int delete(Long id);

    List<Role> selectRolesByUserIdAndRoleEnabled(@Param("userId")Long userId,@Param("enable")Integer enable);

    //根据两个条件来查询，两个条件都有可能为空，如果都为空不返回结果
    List<User> selectByUserIdOrUserName(@Param("userId")Long userId, @Param("userName")String userName);
}
