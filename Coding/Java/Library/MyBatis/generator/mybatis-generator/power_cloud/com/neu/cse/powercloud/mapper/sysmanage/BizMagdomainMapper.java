package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizMagdomain;
import com.neu.cse.powercloud.pojo.sysmanage.BizMagdomainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizMagdomainMapper {
    long countByExample(BizMagdomainExample example);

    int deleteByExample(BizMagdomainExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizMagdomain record);

    int insertSelective(BizMagdomain record);

    List<BizMagdomain> selectByExample(BizMagdomainExample example);

    BizMagdomain selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizMagdomain record, @Param("example") BizMagdomainExample example);

    int updateByExample(@Param("record") BizMagdomain record, @Param("example") BizMagdomainExample example);

    int updateByPrimaryKeySelective(BizMagdomain record);

    int updateByPrimaryKey(BizMagdomain record);
}