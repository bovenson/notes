package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceElecmeter;
import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceElecmeterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceElecmeterMapper {
    long countByExample(BizDeviceElecmeterExample example);

    int deleteByExample(BizDeviceElecmeterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDeviceElecmeter record);

    int insertSelective(BizDeviceElecmeter record);

    List<BizDeviceElecmeter> selectByExample(BizDeviceElecmeterExample example);

    BizDeviceElecmeter selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDeviceElecmeter record, @Param("example") BizDeviceElecmeterExample example);

    int updateByExample(@Param("record") BizDeviceElecmeter record, @Param("example") BizDeviceElecmeterExample example);

    int updateByPrimaryKeySelective(BizDeviceElecmeter record);

    int updateByPrimaryKey(BizDeviceElecmeter record);
}