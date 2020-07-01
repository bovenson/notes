package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizElectricianPosition;
import com.neu.cse.powercloud.pojo.sysmanage.BizElectricianPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizElectricianPositionMapper {
    long countByExample(BizElectricianPositionExample example);

    int deleteByExample(BizElectricianPositionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizElectricianPosition record);

    int insertSelective(BizElectricianPosition record);

    List<BizElectricianPosition> selectByExample(BizElectricianPositionExample example);

    BizElectricianPosition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizElectricianPosition record, @Param("example") BizElectricianPositionExample example);

    int updateByExample(@Param("record") BizElectricianPosition record, @Param("example") BizElectricianPositionExample example);

    int updateByPrimaryKeySelective(BizElectricianPosition record);

    int updateByPrimaryKey(BizElectricianPosition record);
}