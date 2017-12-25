package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizMagdomainCustomer;
import com.neu.cse.powercloud.pojo.sysmanage.BizMagdomainCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizMagdomainCustomerMapper {
    long countByExample(BizMagdomainCustomerExample example);

    int deleteByExample(BizMagdomainCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizMagdomainCustomer record);

    int insertSelective(BizMagdomainCustomer record);

    List<BizMagdomainCustomer> selectByExample(BizMagdomainCustomerExample example);

    BizMagdomainCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizMagdomainCustomer record, @Param("example") BizMagdomainCustomerExample example);

    int updateByExample(@Param("record") BizMagdomainCustomer record, @Param("example") BizMagdomainCustomerExample example);

    int updateByPrimaryKeySelective(BizMagdomainCustomer record);

    int updateByPrimaryKey(BizMagdomainCustomer record);
}