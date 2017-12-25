package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.List;

public class BizDeviceExceptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceExceptionExample() {
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

        public Criteria andDeviceidIsNull() {
            addCriterion("deviceID is null");
            return (Criteria) this;
        }

        public Criteria andDeviceidIsNotNull() {
            addCriterion("deviceID is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceidEqualTo(Integer value) {
            addCriterion("deviceID =", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotEqualTo(Integer value) {
            addCriterion("deviceID <>", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThan(Integer value) {
            addCriterion("deviceID >", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("deviceID >=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThan(Integer value) {
            addCriterion("deviceID <", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidLessThanOrEqualTo(Integer value) {
            addCriterion("deviceID <=", value, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidIn(List<Integer> values) {
            addCriterion("deviceID in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotIn(List<Integer> values) {
            addCriterion("deviceID not in", values, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidBetween(Integer value1, Integer value2) {
            addCriterion("deviceID between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andDeviceidNotBetween(Integer value1, Integer value2) {
            addCriterion("deviceID not between", value1, value2, "deviceid");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andCondationIsNull() {
            addCriterion("condation is null");
            return (Criteria) this;
        }

        public Criteria andCondationIsNotNull() {
            addCriterion("condation is not null");
            return (Criteria) this;
        }

        public Criteria andCondationEqualTo(String value) {
            addCriterion("condation =", value, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationNotEqualTo(String value) {
            addCriterion("condation <>", value, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationGreaterThan(String value) {
            addCriterion("condation >", value, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationGreaterThanOrEqualTo(String value) {
            addCriterion("condation >=", value, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationLessThan(String value) {
            addCriterion("condation <", value, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationLessThanOrEqualTo(String value) {
            addCriterion("condation <=", value, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationLike(String value) {
            addCriterion("condation like", value, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationNotLike(String value) {
            addCriterion("condation not like", value, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationIn(List<String> values) {
            addCriterion("condation in", values, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationNotIn(List<String> values) {
            addCriterion("condation not in", values, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationBetween(String value1, String value2) {
            addCriterion("condation between", value1, value2, "condation");
            return (Criteria) this;
        }

        public Criteria andCondationNotBetween(String value1, String value2) {
            addCriterion("condation not between", value1, value2, "condation");
            return (Criteria) this;
        }

        public Criteria andValue1IsNull() {
            addCriterion("value1 is null");
            return (Criteria) this;
        }

        public Criteria andValue1IsNotNull() {
            addCriterion("value1 is not null");
            return (Criteria) this;
        }

        public Criteria andValue1EqualTo(String value) {
            addCriterion("value1 =", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1NotEqualTo(String value) {
            addCriterion("value1 <>", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1GreaterThan(String value) {
            addCriterion("value1 >", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1GreaterThanOrEqualTo(String value) {
            addCriterion("value1 >=", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1LessThan(String value) {
            addCriterion("value1 <", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1LessThanOrEqualTo(String value) {
            addCriterion("value1 <=", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1Like(String value) {
            addCriterion("value1 like", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1NotLike(String value) {
            addCriterion("value1 not like", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1In(List<String> values) {
            addCriterion("value1 in", values, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1NotIn(List<String> values) {
            addCriterion("value1 not in", values, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1Between(String value1, String value2) {
            addCriterion("value1 between", value1, value2, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1NotBetween(String value1, String value2) {
            addCriterion("value1 not between", value1, value2, "value1");
            return (Criteria) this;
        }

        public Criteria andValue2IsNull() {
            addCriterion("value2 is null");
            return (Criteria) this;
        }

        public Criteria andValue2IsNotNull() {
            addCriterion("value2 is not null");
            return (Criteria) this;
        }

        public Criteria andValue2EqualTo(String value) {
            addCriterion("value2 =", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2NotEqualTo(String value) {
            addCriterion("value2 <>", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2GreaterThan(String value) {
            addCriterion("value2 >", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2GreaterThanOrEqualTo(String value) {
            addCriterion("value2 >=", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2LessThan(String value) {
            addCriterion("value2 <", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2LessThanOrEqualTo(String value) {
            addCriterion("value2 <=", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2Like(String value) {
            addCriterion("value2 like", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2NotLike(String value) {
            addCriterion("value2 not like", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2In(List<String> values) {
            addCriterion("value2 in", values, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2NotIn(List<String> values) {
            addCriterion("value2 not in", values, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2Between(String value1, String value2) {
            addCriterion("value2 between", value1, value2, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2NotBetween(String value1, String value2) {
            addCriterion("value2 not between", value1, value2, "value2");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeIsNull() {
            addCriterion("exceptionCode is null");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeIsNotNull() {
            addCriterion("exceptionCode is not null");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeEqualTo(String value) {
            addCriterion("exceptionCode =", value, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeNotEqualTo(String value) {
            addCriterion("exceptionCode <>", value, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeGreaterThan(String value) {
            addCriterion("exceptionCode >", value, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeGreaterThanOrEqualTo(String value) {
            addCriterion("exceptionCode >=", value, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeLessThan(String value) {
            addCriterion("exceptionCode <", value, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeLessThanOrEqualTo(String value) {
            addCriterion("exceptionCode <=", value, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeLike(String value) {
            addCriterion("exceptionCode like", value, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeNotLike(String value) {
            addCriterion("exceptionCode not like", value, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeIn(List<String> values) {
            addCriterion("exceptionCode in", values, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeNotIn(List<String> values) {
            addCriterion("exceptionCode not in", values, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeBetween(String value1, String value2) {
            addCriterion("exceptionCode between", value1, value2, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andExceptioncodeNotBetween(String value1, String value2) {
            addCriterion("exceptionCode not between", value1, value2, "exceptioncode");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("desc is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("desc is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("desc =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("desc <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("desc >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("desc >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("desc <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("desc <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("desc like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("desc not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("desc in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("desc not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("desc between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("desc not between", value1, value2, "desc");
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