package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizWorktask;
import com.neu.cse.powercloud.pojo.sysmanage.BizWorktaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizWorktaskMapper {
    long countByExample(BizWorktaskExample example);

    int deleteByExample(BizWorktaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizWorktask record);

    int insertSelective(BizWorktask record);

    List<BizWorktask> selectByExample(BizWorktaskExample example);

    BizWorktask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizWorktask record, @Param("example") BizWorktaskExample example);

    int updateByExample(@Param("record") BizWorktask record, @Param("example") BizWorktaskExample example);

    int updateByPrimaryKeySelective(BizWorktask record);

    int updateByPrimaryKey(BizWorktask record);
}