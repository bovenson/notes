package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizData;
import com.neu.cse.powercloud.pojo.sysmanage.BizDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDataMapper {
    long countByExample(BizDataExample example);

    int deleteByExample(BizDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizData record);

    int insertSelective(BizData record);

    List<BizData> selectByExample(BizDataExample example);

    BizData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizData record, @Param("example") BizDataExample example);

    int updateByExample(@Param("record") BizData record, @Param("example") BizDataExample example);

    int updateByPrimaryKeySelective(BizData record);

    int updateByPrimaryKey(BizData record);
}