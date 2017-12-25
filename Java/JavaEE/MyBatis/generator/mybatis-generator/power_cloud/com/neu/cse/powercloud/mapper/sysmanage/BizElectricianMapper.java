package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizElectrician;
import com.neu.cse.powercloud.pojo.sysmanage.BizElectricianExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizElectricianMapper {
    long countByExample(BizElectricianExample example);

    int deleteByExample(BizElectricianExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizElectrician record);

    int insertSelective(BizElectrician record);

    List<BizElectrician> selectByExample(BizElectricianExample example);

    BizElectrician selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizElectrician record, @Param("example") BizElectricianExample example);

    int updateByExample(@Param("record") BizElectrician record, @Param("example") BizElectricianExample example);

    int updateByPrimaryKeySelective(BizElectrician record);

    int updateByPrimaryKey(BizElectrician record);
}