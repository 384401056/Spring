package com.blueice.mapper;

import com.blueice.model.SysUser;
import java.util.List;

public interface SysUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Wed Oct 24 18:08:38 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Wed Oct 24 18:08:38 CST 2018
     */
    int insert(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Wed Oct 24 18:08:38 CST 2018
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Wed Oct 24 18:08:38 CST 2018
     */
    List<SysUser> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Wed Oct 24 18:08:38 CST 2018
     */
    int updateByPrimaryKey(SysUser record);

    /**
     * 调用存储过程
     * @param user
     */
    void selectUserById(SysUser user);



}