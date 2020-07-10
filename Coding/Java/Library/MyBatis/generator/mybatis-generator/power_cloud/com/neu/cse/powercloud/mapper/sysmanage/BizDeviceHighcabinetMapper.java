package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceHighcabinet;
import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceHighcabinetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceHighcabinetMapper {
    long countByExample(BizDeviceHighcabinetExample example);

    int deleteByExample(BizDeviceHighcabinetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDeviceHighcabinet record);

    int insertSelective(BizDeviceHighcabinet record);

    List<BizDeviceHighcabinet> selectByExample(BizDeviceHighcabinetExample example);

    BizDeviceHighcabinet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDeviceHighcabinet record, @Param("example") BizDeviceHighcabinetExample example);

    int updateByExample(@Param("record") BizDeviceHighcabinet record, @Param("example") BizDeviceHighcabinetExample example);

    int updateByPrimaryKeySelective(BizDeviceHighcabinet record);

    int updateByPrimaryKey(BizDeviceHighcabinet record);
}