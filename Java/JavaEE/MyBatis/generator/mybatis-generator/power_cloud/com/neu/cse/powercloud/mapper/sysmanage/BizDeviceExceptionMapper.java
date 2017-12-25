package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceException;
import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceExceptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceExceptionMapper {
    long countByExample(BizDeviceExceptionExample example);

    int deleteByExample(BizDeviceExceptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDeviceException record);

    int insertSelective(BizDeviceException record);

    List<BizDeviceException> selectByExample(BizDeviceExceptionExample example);

    BizDeviceException selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDeviceException record, @Param("example") BizDeviceExceptionExample example);

    int updateByExample(@Param("record") BizDeviceException record, @Param("example") BizDeviceExceptionExample example);

    int updateByPrimaryKeySelective(BizDeviceException record);

    int updateByPrimaryKey(BizDeviceException record);
}