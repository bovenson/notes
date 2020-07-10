package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.SysUserDataAuth;
import com.neu.cse.powercloud.pojo.sysmanage.SysUserDataAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserDataAuthMapper {
    long countByExample(SysUserDataAuthExample example);

    int deleteByExample(SysUserDataAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserDataAuth record);

    int insertSelective(SysUserDataAuth record);

    List<SysUserDataAuth> selectByExample(SysUserDataAuthExample example);

    SysUserDataAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysUserDataAuth record, @Param("example") SysUserDataAuthExample example);

    int updateByExample(@Param("record") SysUserDataAuth record, @Param("example") SysUserDataAuthExample example);

    int updateByPrimaryKeySelective(SysUserDataAuth record);

    int updateByPrimaryKey(SysUserDataAuth record);
}