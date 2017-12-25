package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BizDeviceHighcabinetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceHighcabinetExample() {
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

        public Criteria andElectricitysubstationidIsNull() {
            addCriterion("electricitySubstationID is null");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidIsNotNull() {
            addCriterion("electricitySubstationID is not null");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidEqualTo(Integer value) {
            addCriterion("electricitySubstationID =", value, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidNotEqualTo(Integer value) {
            addCriterion("electricitySubstationID <>", value, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidGreaterThan(Integer value) {
            addCriterion("electricitySubstationID >", value, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidGreaterThanOrEqualTo(Integer value) {
            addCriterion("electricitySubstationID >=", value, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidLessThan(Integer value) {
            addCriterion("electricitySubstationID <", value, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidLessThanOrEqualTo(Integer value) {
            addCriterion("electricitySubstationID <=", value, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidIn(List<Integer> values) {
            addCriterion("electricitySubstationID in", values, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidNotIn(List<Integer> values) {
            addCriterion("electricitySubstationID not in", values, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidBetween(Integer value1, Integer value2) {
            addCriterion("electricitySubstationID between", value1, value2, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andElectricitysubstationidNotBetween(Integer value1, Integer value2) {
            addCriterion("electricitySubstationID not between", value1, value2, "electricitysubstationid");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeIsNull() {
            addCriterion("lowcabinetType is null");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeIsNotNull() {
            addCriterion("lowcabinetType is not null");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeEqualTo(String value) {
            addCriterion("lowcabinetType =", value, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeNotEqualTo(String value) {
            addCriterion("lowcabinetType <>", value, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeGreaterThan(String value) {
            addCriterion("lowcabinetType >", value, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeGreaterThanOrEqualTo(String value) {
            addCriterion("lowcabinetType >=", value, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeLessThan(String value) {
            addCriterion("lowcabinetType <", value, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeLessThanOrEqualTo(String value) {
            addCriterion("lowcabinetType <=", value, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeLike(String value) {
            addCriterion("lowcabinetType like", value, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeNotLike(String value) {
            addCriterion("lowcabinetType not like", value, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeIn(List<String> values) {
            addCriterion("lowcabinetType in", values, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeNotIn(List<String> values) {
            addCriterion("lowcabinetType not in", values, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeBetween(String value1, String value2) {
            addCriterion("lowcabinetType between", value1, value2, "lowcabinettype");
            return (Criteria) this;
        }

        public Criteria andLowcabinettypeNotBetween(String value1, String value2) {
            addCriterion("lowcabinetType not between", value1, value2, "lowcabinettype");
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

        public Criteria andHighcabinetmodelIsNull() {
            addCriterion("highCabinetModel is null");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelIsNotNull() {
            addCriterion("highCabinetModel is not null");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelEqualTo(String value) {
            addCriterion("highCabinetModel =", value, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelNotEqualTo(String value) {
            addCriterion("highCabinetModel <>", value, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelGreaterThan(String value) {
            addCriterion("highCabinetModel >", value, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelGreaterThanOrEqualTo(String value) {
            addCriterion("highCabinetModel >=", value, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelLessThan(String value) {
            addCriterion("highCabinetModel <", value, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelLessThanOrEqualTo(String value) {
            addCriterion("highCabinetModel <=", value, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelLike(String value) {
            addCriterion("highCabinetModel like", value, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelNotLike(String value) {
            addCriterion("highCabinetModel not like", value, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelIn(List<String> values) {
            addCriterion("highCabinetModel in", values, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelNotIn(List<String> values) {
            addCriterion("highCabinetModel not in", values, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelBetween(String value1, String value2) {
            addCriterion("highCabinetModel between", value1, value2, "highcabinetmodel");
            return (Criteria) this;
        }

        public Criteria andHighcabinetmodelNotBetween(String value1, String value2) {
            addCriterion("highCabinetModel not between", value1, value2, "highcabinetmodel");
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

        public Criteria andSpecificationsIsNull() {
            addCriterion("specifications is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNotNull() {
            addCriterion("specifications is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsEqualTo(String value) {
            addCriterion("specifications =", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotEqualTo(String value) {
            addCriterion("specifications <>", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThan(String value) {
            addCriterion("specifications >", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("specifications >=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThan(String value) {
            addCriterion("specifications <", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("specifications <=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLike(String value) {
            addCriterion("specifications like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotLike(String value) {
            addCriterion("specifications not like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIn(List<String> values) {
            addCriterion("specifications in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotIn(List<String> values) {
            addCriterion("specifications not in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsBetween(String value1, String value2) {
            addCriterion("specifications between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotBetween(String value1, String value2) {
            addCriterion("specifications not between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andDiagramIsNull() {
            addCriterion("diagram is null");
            return (Criteria) this;
        }

        public Criteria andDiagramIsNotNull() {
            addCriterion("diagram is not null");
            return (Criteria) this;
        }

        public Criteria andDiagramEqualTo(String value) {
            addCriterion("diagram =", value, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramNotEqualTo(String value) {
            addCriterion("diagram <>", value, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramGreaterThan(String value) {
            addCriterion("diagram >", value, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramGreaterThanOrEqualTo(String value) {
            addCriterion("diagram >=", value, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramLessThan(String value) {
            addCriterion("diagram <", value, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramLessThanOrEqualTo(String value) {
            addCriterion("diagram <=", value, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramLike(String value) {
            addCriterion("diagram like", value, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramNotLike(String value) {
            addCriterion("diagram not like", value, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramIn(List<String> values) {
            addCriterion("diagram in", values, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramNotIn(List<String> values) {
            addCriterion("diagram not in", values, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramBetween(String value1, String value2) {
            addCriterion("diagram between", value1, value2, "diagram");
            return (Criteria) this;
        }

        public Criteria andDiagramNotBetween(String value1, String value2) {
            addCriterion("diagram not between", value1, value2, "diagram");
            return (Criteria) this;
        }

        public Criteria andVoltageclassIsNull() {
            addCriterion("voltageClass is null");
            return (Criteria) this;
        }

        public Criteria andVoltageclassIsNotNull() {
            addCriterion("voltageClass is not null");
            return (Criteria) this;
        }

        public Criteria andVoltageclassEqualTo(String value) {
            addCriterion("voltageClass =", value, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassNotEqualTo(String value) {
            addCriterion("voltageClass <>", value, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassGreaterThan(String value) {
            addCriterion("voltageClass >", value, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassGreaterThanOrEqualTo(String value) {
            addCriterion("voltageClass >=", value, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassLessThan(String value) {
            addCriterion("voltageClass <", value, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassLessThanOrEqualTo(String value) {
            addCriterion("voltageClass <=", value, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassLike(String value) {
            addCriterion("voltageClass like", value, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassNotLike(String value) {
            addCriterion("voltageClass not like", value, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassIn(List<String> values) {
            addCriterion("voltageClass in", values, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassNotIn(List<String> values) {
            addCriterion("voltageClass not in", values, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassBetween(String value1, String value2) {
            addCriterion("voltageClass between", value1, value2, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andVoltageclassNotBetween(String value1, String value2) {
            addCriterion("voltageClass not between", value1, value2, "voltageclass");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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