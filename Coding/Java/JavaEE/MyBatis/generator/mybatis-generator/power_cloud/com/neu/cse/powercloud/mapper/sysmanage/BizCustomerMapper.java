package com.neu.cse.powercloud.mapper.sysmanage;

import com.neu.cse.powercloud.pojo.sysmanage.BizCustomer;
import com.neu.cse.powercloud.pojo.sysmanage.BizCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizCustomerMapper {
    long countByExample(BizCustomerExample example);

    int deleteByExample(BizCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BizCustomer record);

    int insertSelective(BizCustomer record);

    List<BizCustomer> selectByExample(BizCustomerExample example);

    BizCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BizCustomer record, @Param("example") BizCustomerExample example);

    int updateByExample(@Param("record") BizCustomer record, @Param("example") BizCustomerExample example);

    int updateByPrimaryKeySelective(BizCustomer record);

    int updateByPrimaryKey(BizCustomer record);
}