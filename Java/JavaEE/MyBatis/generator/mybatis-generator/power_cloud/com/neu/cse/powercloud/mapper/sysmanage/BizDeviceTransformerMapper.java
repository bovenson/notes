package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceTransformer;
import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceTransformerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceTransformerMapper {
    long countByExample(BizDeviceTransformerExample example);

    int deleteByExample(BizDeviceTransformerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDeviceTransformer record);

    int insertSelective(BizDeviceTransformer record);

    List<BizDeviceTransformer> selectByExample(BizDeviceTransformerExample example);

    BizDeviceTransformer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDeviceTransformer record, @Param("example") BizDeviceTransformerExample example);

    int updateByExample(@Param("record") BizDeviceTransformer record, @Param("example") BizDeviceTransformerExample example);

    int updateByPrimaryKeySelective(BizDeviceTransformer record);

    int updateByPrimaryKey(BizDeviceTransformer record);
}