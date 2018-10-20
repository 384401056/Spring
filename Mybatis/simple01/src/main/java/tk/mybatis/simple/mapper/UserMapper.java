package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.User;

import java.util.List;

public interface UserMapper {

    User selectById(Long id);

    List<User> selectAll();
}
