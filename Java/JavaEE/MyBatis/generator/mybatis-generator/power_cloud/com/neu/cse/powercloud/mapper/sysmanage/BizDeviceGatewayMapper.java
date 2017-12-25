package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceGateway;
import com.neu.cse.powercloud.pojo.sysmanage.BizDeviceGatewayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceGatewayMapper {
    long countByExample(BizDeviceGatewayExample example);

    int deleteByExample(BizDeviceGatewayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizDeviceGateway record);

    int insertSelective(BizDeviceGateway record);

    List<BizDeviceGateway> selectByExample(BizDeviceGatewayExample example);

    BizDeviceGateway selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizDeviceGateway record, @Param("example") BizDeviceGatewayExample example);

    int updateByExample(@Param("record") BizDeviceGateway record, @Param("example") BizDeviceGatewayExample example);

    int updateByPrimaryKeySelective(BizDeviceGateway record);

    int updateByPrimaryKey(BizDeviceGateway record);
}