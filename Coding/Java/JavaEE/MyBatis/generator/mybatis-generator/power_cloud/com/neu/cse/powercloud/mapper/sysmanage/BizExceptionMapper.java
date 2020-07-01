package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizException;
import com.neu.cse.powercloud.pojo.sysmanage.BizExceptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizExceptionMapper {
    long countByExample(BizExceptionExample example);

    int deleteByExample(BizExceptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizException record);

    int insertSelective(BizException record);

    List<BizException> selectByExample(BizExceptionExample example);

    BizException selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizException record, @Param("example") BizExceptionExample example);

    int updateByExample(@Param("record") BizException record, @Param("example") BizExceptionExample example);

    int updateByPrimaryKeySelective(BizException record);

    int updateByPrimaryKey(BizException record);
}