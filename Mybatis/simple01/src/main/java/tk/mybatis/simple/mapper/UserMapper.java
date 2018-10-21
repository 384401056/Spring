package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.Role;
import tk.mybatis.simple.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    //当参数是list类型时，可以用@Param指定别名，这样在xml中可以使用。
    List<User> selectByIdList(@Param("myList")List<Long> ids);

    //批量插入数据
    int insertListUser(@Param("userList")List<User> userList);

    //根据Map参数进行动态更新数据，当用map作为参数时,在xml中的collection属性就要赋值为:_parameter.
    //这里指定别名，并在xml中的collection属性中使用，会报错
    int updateByMap(Map<String, Object> userMap);
}
