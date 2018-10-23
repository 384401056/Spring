package com.blueice.mapper;

import com.blueice.model.SysUser;
import com.blueice.model.SysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    long countByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    int deleteByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    int insert(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    int insertSelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    List<SysUser> selectByExampleWithBLOBs(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    List<SysUser> selectByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:34 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:35 CST 2018
     */
    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:35 CST 2018
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:35 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated Tue Oct 23 03:48:35 CST 2018
     */
    int updateByPrimaryKey(SysUser record);

    //假设用户权限是一对一关系，进行一对一映射查询
    SysUser selectUserAndRoleById(Long id); //自动映射

    SysUser selectUserAndRoleById2(Long id);//通过association映射

    SysUser userRoleMapSelect(Long id);//association中嵌套查询

    SysUser userRoleMapSelectLazy(Long id);//association中嵌套查询之懒加载
}