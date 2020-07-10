package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizService;
import com.neu.cse.powercloud.pojo.sysmanage.BizServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizServiceMapper {
    long countByExample(BizServiceExample example);

    int deleteByExample(BizServiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizService record);

    int insertSelective(BizService record);

    List<BizService> selectByExample(BizServiceExample example);

    BizService selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizService record, @Param("example") BizServiceExample example);

    int updateByExample(@Param("record") BizService record, @Param("example") BizServiceExample example);

    int updateByPrimaryKeySelective(BizService record);

    int updateByPrimaryKey(BizService record);
}