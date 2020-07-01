package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.SysDataAuthority;
import com.neu.cse.powercloud.pojo.sysmanage.SysDataAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDataAuthorityMapper {
    long countByExample(SysDataAuthorityExample example);

    int deleteByExample(SysDataAuthorityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysDataAuthority record);

    int insertSelective(SysDataAuthority record);

    List<SysDataAuthority> selectByExample(SysDataAuthorityExample example);

    SysDataAuthority selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysDataAuthority record, @Param("example") SysDataAuthorityExample example);

    int updateByExample(@Param("record") SysDataAuthority record, @Param("example") SysDataAuthorityExample example);

    int updateByPrimaryKeySelective(SysDataAuthority record);

    int updateByPrimaryKey(SysDataAuthority record);
}