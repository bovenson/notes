package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceLowcabinet;
import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceLowcabinetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceLowcabinetMapper {
    long countByExample(BizDeviceLowcabinetExample example);

    int deleteByExample(BizDeviceLowcabinetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDeviceLowcabinet record);

    int insertSelective(BizDeviceLowcabinet record);

    List<BizDeviceLowcabinet> selectByExample(BizDeviceLowcabinetExample example);

    BizDeviceLowcabinet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDeviceLowcabinet record, @Param("example") BizDeviceLowcabinetExample example);

    int updateByExample(@Param("record") BizDeviceLowcabinet record, @Param("example") BizDeviceLowcabinetExample example);

    int updateByPrimaryKeySelective(BizDeviceLowcabinet record);

    int updateByPrimaryKey(BizDeviceLowcabinet record);
}