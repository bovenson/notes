package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizCodetask;
import com.neu.cse.powercloud.pojo.sysmanage.BizCodetaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizCodetaskMapper {
    long countByExample(BizCodetaskExample example);

    int deleteByExample(BizCodetaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizCodetask record);

    int insertSelective(BizCodetask record);

    List<BizCodetask> selectByExample(BizCodetaskExample example);

    BizCodetask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizCodetask record, @Param("example") BizCodetaskExample example);

    int updateByExample(@Param("record") BizCodetask record, @Param("example") BizCodetaskExample example);

    int updateByPrimaryKeySelective(BizCodetask record);

    int updateByPrimaryKey(BizCodetask record);
}