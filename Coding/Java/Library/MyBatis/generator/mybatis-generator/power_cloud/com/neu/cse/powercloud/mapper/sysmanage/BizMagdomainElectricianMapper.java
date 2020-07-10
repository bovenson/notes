package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizMagdomainElectrician;
import com.neu.cse.powercloud.pojo.sysmanage.BizMagdomainElectricianExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizMagdomainElectricianMapper {
    long countByExample(BizMagdomainElectricianExample example);

    int deleteByExample(BizMagdomainElectricianExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizMagdomainElectrician record);

    int insertSelective(BizMagdomainElectrician record);

    List<BizMagdomainElectrician> selectByExample(BizMagdomainElectricianExample example);

    BizMagdomainElectrician selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizMagdomainElectrician record, @Param("example") BizMagdomainElectricianExample example);

    int updateByExample(@Param("record") BizMagdomainElectrician record, @Param("example") BizMagdomainElectricianExample example);

    int updateByPrimaryKeySelective(BizMagdomainElectrician record);

    int updateByPrimaryKey(BizMagdomainElectrician record);
}