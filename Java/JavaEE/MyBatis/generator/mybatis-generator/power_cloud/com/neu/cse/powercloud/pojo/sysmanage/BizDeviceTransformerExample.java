package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BizDeviceTransformerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceTransformerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("manufacturer is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("manufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("manufacturer =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("manufacturer <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("manufacturer >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("manufacturer >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("manufacturer <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("manufacturer <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("manufacturer like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("manufacturer not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("manufacturer in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("manufacturer not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("manufacturer between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("manufacturer not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeIsNull() {
            addCriterion("manufacturerCode is null");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeIsNotNull() {
            addCriterion("manufacturerCode is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeEqualTo(String value) {
            addCriterion("manufacturerCode =", value, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeNotEqualTo(String value) {
            addCriterion("manufacturerCode <>", value, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeGreaterThan(String value) {
            addCriterion("manufacturerCode >", value, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeGreaterThanOrEqualTo(String value) {
            addCriterion("manufacturerCode >=", value, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeLessThan(String value) {
            addCriterion("manufacturerCode <", value, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeLessThanOrEqualTo(String value) {
            addCriterion("manufacturerCode <=", value, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeLike(String value) {
            addCriterion("manufacturerCode like", value, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeNotLike(String value) {
            addCriterion("manufacturerCode not like", value, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeIn(List<String> values) {
            addCriterion("manufacturerCode in", values, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeNotIn(List<String> values) {
            addCriterion("manufacturerCode not in", values, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeBetween(String value1, String value2) {
            addCriterion("manufacturerCode between", value1, value2, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andManufacturercodeNotBetween(String value1, String value2) {
            addCriterion("manufacturerCode not between", value1, value2, "manufacturercode");
            return (Criteria) this;
        }

        public Criteria andTransformermodelIsNull() {
            addCriterion("transformerModel is null");
            return (Criteria) this;
        }

        public Criteria andTransformermodelIsNotNull() {
            addCriterion("transformerModel is not null");
            return (Criteria) this;
        }

        public Criteria andTransformermodelEqualTo(String value) {
            addCriterion("transformerModel =", value, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelNotEqualTo(String value) {
            addCriterion("transformerModel <>", value, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelGreaterThan(String value) {
            addCriterion("transformerModel >", value, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelGreaterThanOrEqualTo(String value) {
            addCriterion("transformerModel >=", value, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelLessThan(String value) {
            addCriterion("transformerModel <", value, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelLessThanOrEqualTo(String value) {
            addCriterion("transformerModel <=", value, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelLike(String value) {
            addCriterion("transformerModel like", value, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelNotLike(String value) {
            addCriterion("transformerModel not like", value, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelIn(List<String> values) {
            addCriterion("transformerModel in", values, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelNotIn(List<String> values) {
            addCriterion("transformerModel not in", values, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelBetween(String value1, String value2) {
            addCriterion("transformerModel between", value1, value2, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andTransformermodelNotBetween(String value1, String value2) {
            addCriterion("transformerModel not between", value1, value2, "transformermodel");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentIsNull() {
            addCriterion("workEnviorment is null");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentIsNotNull() {
            addCriterion("workEnviorment is not null");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentEqualTo(String value) {
            addCriterion("workEnviorment =", value, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentNotEqualTo(String value) {
            addCriterion("workEnviorment <>", value, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentGreaterThan(String value) {
            addCriterion("workEnviorment >", value, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentGreaterThanOrEqualTo(String value) {
            addCriterion("workEnviorment >=", value, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentLessThan(String value) {
            addCriterion("workEnviorment <", value, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentLessThanOrEqualTo(String value) {
            addCriterion("workEnviorment <=", value, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentLike(String value) {
            addCriterion("workEnviorment like", value, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentNotLike(String value) {
            addCriterion("workEnviorment not like", value, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentIn(List<String> values) {
            addCriterion("workEnviorment in", values, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentNotIn(List<String> values) {
            addCriterion("workEnviorment not in", values, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentBetween(String value1, String value2) {
            addCriterion("workEnviorment between", value1, value2, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorkenviormentNotBetween(String value1, String value2) {
            addCriterion("workEnviorment not between", value1, value2, "workenviorment");
            return (Criteria) this;
        }

        public Criteria andWorktypeIsNull() {
            addCriterion("workType is null");
            return (Criteria) this;
        }

        public Criteria andWorktypeIsNotNull() {
            addCriterion("workType is not null");
            return (Criteria) this;
        }

        public Criteria andWorktypeEqualTo(String value) {
            addCriterion("workType =", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeNotEqualTo(String value) {
            addCriterion("workType <>", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeGreaterThan(String value) {
            addCriterion("workType >", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeGreaterThanOrEqualTo(String value) {
            addCriterion("workType >=", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeLessThan(String value) {
            addCriterion("workType <", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeLessThanOrEqualTo(String value) {
            addCriterion("workType <=", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeLike(String value) {
            addCriterion("workType like", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeNotLike(String value) {
            addCriterion("workType not like", value, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeIn(List<String> values) {
            addCriterion("workType in", values, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeNotIn(List<String> values) {
            addCriterion("workType not in", values, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeBetween(String value1, String value2) {
            addCriterion("workType between", value1, value2, "worktype");
            return (Criteria) this;
        }

        public Criteria andWorktypeNotBetween(String value1, String value2) {
            addCriterion("workType not between", value1, value2, "worktype");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageIsNull() {
            addCriterion("ratedVoltage is null");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageIsNotNull() {
            addCriterion("ratedVoltage is not null");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageEqualTo(String value) {
            addCriterion("ratedVoltage =", value, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageNotEqualTo(String value) {
            addCriterion("ratedVoltage <>", value, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageGreaterThan(String value) {
            addCriterion("ratedVoltage >", value, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageGreaterThanOrEqualTo(String value) {
            addCriterion("ratedVoltage >=", value, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageLessThan(String value) {
            addCriterion("ratedVoltage <", value, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageLessThanOrEqualTo(String value) {
            addCriterion("ratedVoltage <=", value, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageLike(String value) {
            addCriterion("ratedVoltage like", value, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageNotLike(String value) {
            addCriterion("ratedVoltage not like", value, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageIn(List<String> values) {
            addCriterion("ratedVoltage in", values, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageNotIn(List<String> values) {
            addCriterion("ratedVoltage not in", values, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageBetween(String value1, String value2) {
            addCriterion("ratedVoltage between", value1, value2, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedvoltageNotBetween(String value1, String value2) {
            addCriterion("ratedVoltage not between", value1, value2, "ratedvoltage");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyIsNull() {
            addCriterion("ratedFrequency is null");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyIsNotNull() {
            addCriterion("ratedFrequency is not null");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyEqualTo(String value) {
            addCriterion("ratedFrequency =", value, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyNotEqualTo(String value) {
            addCriterion("ratedFrequency <>", value, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyGreaterThan(String value) {
            addCriterion("ratedFrequency >", value, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyGreaterThanOrEqualTo(String value) {
            addCriterion("ratedFrequency >=", value, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyLessThan(String value) {
            addCriterion("ratedFrequency <", value, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyLessThanOrEqualTo(String value) {
            addCriterion("ratedFrequency <=", value, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyLike(String value) {
            addCriterion("ratedFrequency like", value, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyNotLike(String value) {
            addCriterion("ratedFrequency not like", value, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyIn(List<String> values) {
            addCriterion("ratedFrequency in", values, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyNotIn(List<String> values) {
            addCriterion("ratedFrequency not in", values, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyBetween(String value1, String value2) {
            addCriterion("ratedFrequency between", value1, value2, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andRatedfrequencyNotBetween(String value1, String value2) {
            addCriterion("ratedFrequency not between", value1, value2, "ratedfrequency");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelIsNull() {
            addCriterion("connectionGroupLabel is null");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelIsNotNull() {
            addCriterion("connectionGroupLabel is not null");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelEqualTo(String value) {
            addCriterion("connectionGroupLabel =", value, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelNotEqualTo(String value) {
            addCriterion("connectionGroupLabel <>", value, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelGreaterThan(String value) {
            addCriterion("connectionGroupLabel >", value, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelGreaterThanOrEqualTo(String value) {
            addCriterion("connectionGroupLabel >=", value, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelLessThan(String value) {
            addCriterion("connectionGroupLabel <", value, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelLessThanOrEqualTo(String value) {
            addCriterion("connectionGroupLabel <=", value, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelLike(String value) {
            addCriterion("connectionGroupLabel like", value, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelNotLike(String value) {
            addCriterion("connectionGroupLabel not like", value, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelIn(List<String> values) {
            addCriterion("connectionGroupLabel in", values, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelNotIn(List<String> values) {
            addCriterion("connectionGroupLabel not in", values, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelBetween(String value1, String value2) {
            addCriterion("connectionGroupLabel between", value1, value2, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andConnectiongrouplabelNotBetween(String value1, String value2) {
            addCriterion("connectionGroupLabel not between", value1, value2, "connectiongrouplabel");
            return (Criteria) this;
        }

        public Criteria andInsulationclassIsNull() {
            addCriterion("InsulationClass is null");
            return (Criteria) this;
        }

        public Criteria andInsulationclassIsNotNull() {
            addCriterion("InsulationClass is not null");
            return (Criteria) this;
        }

        public Criteria andInsulationclassEqualTo(String value) {
            addCriterion("InsulationClass =", value, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassNotEqualTo(String value) {
            addCriterion("InsulationClass <>", value, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassGreaterThan(String value) {
            addCriterion("InsulationClass >", value, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassGreaterThanOrEqualTo(String value) {
            addCriterion("InsulationClass >=", value, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassLessThan(String value) {
            addCriterion("InsulationClass <", value, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassLessThanOrEqualTo(String value) {
            addCriterion("InsulationClass <=", value, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassLike(String value) {
            addCriterion("InsulationClass like", value, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassNotLike(String value) {
            addCriterion("InsulationClass not like", value, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassIn(List<String> values) {
            addCriterion("InsulationClass in", values, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassNotIn(List<String> values) {
            addCriterion("InsulationClass not in", values, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassBetween(String value1, String value2) {
            addCriterion("InsulationClass between", value1, value2, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andInsulationclassNotBetween(String value1, String value2) {
            addCriterion("InsulationClass not between", value1, value2, "insulationclass");
            return (Criteria) this;
        }

        public Criteria andManufacturdateIsNull() {
            addCriterion("manufacturDate is null");
            return (Criteria) this;
        }

        public Criteria andManufacturdateIsNotNull() {
            addCriterion("manufacturDate is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturdateEqualTo(Date value) {
            addCriterionForJDBCDate("manufacturDate =", value, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("manufacturDate <>", value, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateGreaterThan(Date value) {
            addCriterionForJDBCDate("manufacturDate >", value, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("manufacturDate >=", value, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateLessThan(Date value) {
            addCriterionForJDBCDate("manufacturDate <", value, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("manufacturDate <=", value, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateIn(List<Date> values) {
            addCriterionForJDBCDate("manufacturDate in", values, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("manufacturDate not in", values, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("manufacturDate between", value1, value2, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andManufacturdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("manufacturDate not between", value1, value2, "manufacturdate");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeIsNull() {
            addCriterion("gatewayCode is null");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeIsNotNull() {
            addCriterion("gatewayCode is not null");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeEqualTo(Integer value) {
            addCriterion("gatewayCode =", value, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeNotEqualTo(Integer value) {
            addCriterion("gatewayCode <>", value, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeGreaterThan(Integer value) {
            addCriterion("gatewayCode >", value, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("gatewayCode >=", value, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeLessThan(Integer value) {
            addCriterion("gatewayCode <", value, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeLessThanOrEqualTo(Integer value) {
            addCriterion("gatewayCode <=", value, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeIn(List<Integer> values) {
            addCriterion("gatewayCode in", values, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeNotIn(List<Integer> values) {
            addCriterion("gatewayCode not in", values, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeBetween(Integer value1, Integer value2) {
            addCriterion("gatewayCode between", value1, value2, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andGatewaycodeNotBetween(Integer value1, Integer value2) {
            addCriterion("gatewayCode not between", value1, value2, "gatewaycode");
            return (Criteria) this;
        }

        public Criteria andBiztypeIsNull() {
            addCriterion("bizType is null");
            return (Criteria) this;
        }

        public Criteria andBiztypeIsNotNull() {
            addCriterion("bizType is not null");
            return (Criteria) this;
        }

        public Criteria andBiztypeEqualTo(String value) {
            addCriterion("bizType =", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeNotEqualTo(String value) {
            addCriterion("bizType <>", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeGreaterThan(String value) {
            addCriterion("bizType >", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeGreaterThanOrEqualTo(String value) {
            addCriterion("bizType >=", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeLessThan(String value) {
            addCriterion("bizType <", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeLessThanOrEqualTo(String value) {
            addCriterion("bizType <=", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeLike(String value) {
            addCriterion("bizType like", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeNotLike(String value) {
            addCriterion("bizType not like", value, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeIn(List<String> values) {
            addCriterion("bizType in", values, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeNotIn(List<String> values) {
            addCriterion("bizType not in", values, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeBetween(String value1, String value2) {
            addCriterion("bizType between", value1, value2, "biztype");
            return (Criteria) this;
        }

        public Criteria andBiztypeNotBetween(String value1, String value2) {
            addCriterion("bizType not between", value1, value2, "biztype");
            return (Criteria) this;
        }

        public Criteria andGatewayidIsNull() {
            addCriterion("gatewayID is null");
            return (Criteria) this;
        }

        public Criteria andGatewayidIsNotNull() {
            addCriterion("gatewayID is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayidEqualTo(Integer value) {
            addCriterion("gatewayID =", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidNotEqualTo(Integer value) {
            addCriterion("gatewayID <>", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidGreaterThan(Integer value) {
            addCriterion("gatewayID >", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidGreaterThanOrEqualTo(Integer value) {
            addCriterion("gatewayID >=", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidLessThan(Integer value) {
            addCriterion("gatewayID <", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidLessThanOrEqualTo(Integer value) {
            addCriterion("gatewayID <=", value, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidIn(List<Integer> values) {
            addCriterion("gatewayID in", values, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidNotIn(List<Integer> values) {
            addCriterion("gatewayID not in", values, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidBetween(Integer value1, Integer value2) {
            addCriterion("gatewayID between", value1, value2, "gatewayid");
            return (Criteria) this;
        }

        public Criteria andGatewayidNotBetween(Integer value1, Integer value2) {
            addCriterion("gatewayID not between", value1, value2, "gatewayid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}