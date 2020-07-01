package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizTypeDevice;
import com.neu.cse.powercloud.pojo.sysmanage.BizTypeDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizTypeDeviceMapper {
    long countByExample(BizTypeDeviceExample example);

    int deleteByExample(BizTypeDeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizTypeDevice record);

    int insertSelective(BizTypeDevice record);

    List<BizTypeDevice> selectByExample(BizTypeDeviceExample example);

    BizTypeDevice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizTypeDevice record, @Param("example") BizTypeDeviceExample example);

    int updateByExample(@Param("record") BizTypeDevice record, @Param("example") BizTypeDeviceExample example);

    int updateByPrimaryKeySelective(BizTypeDevice record);

    int updateByPrimaryKey(BizTypeDevice record);
}