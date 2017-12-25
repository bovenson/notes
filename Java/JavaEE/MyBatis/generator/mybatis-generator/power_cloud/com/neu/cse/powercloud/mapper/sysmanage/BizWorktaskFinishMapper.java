package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizWorktaskFinish;
import com.neu.cse.powercloud.pojo.sysmanage.BizWorktaskFinishExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizWorktaskFinishMapper {
    long countByExample(BizWorktaskFinishExample example);

    int deleteByExample(BizWorktaskFinishExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizWorktaskFinish record);

    int insertSelective(BizWorktaskFinish record);

    List<BizWorktaskFinish> selectByExample(BizWorktaskFinishExample example);

    BizWorktaskFinish selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizWorktaskFinish record, @Param("example") BizWorktaskFinishExample example);

    int updateByExample(@Param("record") BizWorktaskFinish record, @Param("example") BizWorktaskFinishExample example);

    int updateByPrimaryKeySelective(BizWorktaskFinish record);

    int updateByPrimaryKey(BizWorktaskFinish record);
}