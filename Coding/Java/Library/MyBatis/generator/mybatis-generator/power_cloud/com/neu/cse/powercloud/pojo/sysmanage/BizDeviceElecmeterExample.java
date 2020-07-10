package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BizDeviceElecmeterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceElecmeterExample() {
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

        public Criteria andCircuitnameIsNull() {
            addCriterion("circuitName is null");
            return (Criteria) this;
        }

        public Criteria andCircuitnameIsNotNull() {
            addCriterion("circuitName is not null");
            return (Criteria) this;
        }

        public Criteria andCircuitnameEqualTo(String value) {
            addCriterion("circuitName =", value, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameNotEqualTo(String value) {
            addCriterion("circuitName <>", value, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameGreaterThan(String value) {
            addCriterion("circuitName >", value, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameGreaterThanOrEqualTo(String value) {
            addCriterion("circuitName >=", value, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameLessThan(String value) {
            addCriterion("circuitName <", value, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameLessThanOrEqualTo(String value) {
            addCriterion("circuitName <=", value, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameLike(String value) {
            addCriterion("circuitName like", value, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameNotLike(String value) {
            addCriterion("circuitName not like", value, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameIn(List<String> values) {
            addCriterion("circuitName in", values, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameNotIn(List<String> values) {
            addCriterion("circuitName not in", values, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameBetween(String value1, String value2) {
            addCriterion("circuitName between", value1, value2, "circuitname");
            return (Criteria) this;
        }

        public Criteria andCircuitnameNotBetween(String value1, String value2) {
            addCriterion("circuitName not between", value1, value2, "circuitname");
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