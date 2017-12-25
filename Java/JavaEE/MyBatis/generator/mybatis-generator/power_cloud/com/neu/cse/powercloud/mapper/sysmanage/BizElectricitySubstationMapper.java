package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizElectricitySubstation;
import com.neu.cse.powercloud.pojo.sysmanage.BizElectricitySubstationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizElectricitySubstationMapper {
    long countByExample(BizElectricitySubstationExample example);

    int deleteByExample(BizElectricitySubstationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizElectricitySubstation record);

    int insertSelective(BizElectricitySubstation record);

    List<BizElectricitySubstation> selectByExample(BizElectricitySubstationExample example);

    BizElectricitySubstation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizElectricitySubstation record, @Param("example") BizElectricitySubstationExample example);

    int updateByExample(@Param("record") BizElectricitySubstation record, @Param("example") BizElectricitySubstationExample example);

    int updateByPrimaryKeySelective(BizElectricitySubstation record);

    int updateByPrimaryKey(BizElectricitySubstation record);
}