package com.blueice.mapper;

import com.blueice.model.SysUserRole;
import java.util.List;

public interface SysUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Wed Oct 24 18:08:38 CST 2018
     */
    int insert(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Wed Oct 24 18:08:38 CST 2018
     */
    List<SysUserRole> selectAll();
}