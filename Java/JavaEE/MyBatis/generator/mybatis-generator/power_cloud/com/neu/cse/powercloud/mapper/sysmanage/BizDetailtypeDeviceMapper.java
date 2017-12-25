package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizDetailtypeDevice;
import com.neu.cse.powercloud.pojo.sysmanage.BizDetailtypeDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDetailtypeDeviceMapper {
    long countByExample(BizDetailtypeDeviceExample example);

    int deleteByExample(BizDetailtypeDeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDetailtypeDevice record);

    int insertSelective(BizDetailtypeDevice record);

    List<BizDetailtypeDevice> selectByExample(BizDetailtypeDeviceExample example);

    BizDetailtypeDevice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDetailtypeDevice record, @Param("example") BizDetailtypeDeviceExample example);

    int updateByExample(@Param("record") BizDetailtypeDevice record, @Param("example") BizDetailtypeDeviceExample example);

    int updateByPrimaryKeySelective(BizDetailtypeDevice record);

    int updateByPrimaryKey(BizDetailtypeDevice record);
}