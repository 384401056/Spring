package com.blueice.mapper;

import com.blueice.model.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Mon Dec 24 17:59:27 CST 2018
     */
    int insert(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Mon Dec 24 17:59:27 CST 2018
     */
    List<SysUserRole> selectAll();
}