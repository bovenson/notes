package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizElectricianPositionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizElectricianPositionExample() {
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

        public Criteria andElectricianidIsNull() {
            addCriterion("electricianID is null");
            return (Criteria) this;
        }

        public Criteria andElectricianidIsNotNull() {
            addCriterion("electricianID is not null");
            return (Criteria) this;
        }

        public Criteria andElectricianidEqualTo(Integer value) {
            addCriterion("electricianID =", value, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidNotEqualTo(Integer value) {
            addCriterion("electricianID <>", value, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidGreaterThan(Integer value) {
            addCriterion("electricianID >", value, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidGreaterThanOrEqualTo(Integer value) {
            addCriterion("electricianID >=", value, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidLessThan(Integer value) {
            addCriterion("electricianID <", value, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidLessThanOrEqualTo(Integer value) {
            addCriterion("electricianID <=", value, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidIn(List<Integer> values) {
            addCriterion("electricianID in", values, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidNotIn(List<Integer> values) {
            addCriterion("electricianID not in", values, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidBetween(Integer value1, Integer value2) {
            addCriterion("electricianID between", value1, value2, "electricianid");
            return (Criteria) this;
        }

        public Criteria andElectricianidNotBetween(Integer value1, Integer value2) {
            addCriterion("electricianID not between", value1, value2, "electricianid");
            return (Criteria) this;
        }

        public Criteria andLacationIsNull() {
            addCriterion("lacation is null");
            return (Criteria) this;
        }

        public Criteria andLacationIsNotNull() {
            addCriterion("lacation is not null");
            return (Criteria) this;
        }

        public Criteria andLacationEqualTo(String value) {
            addCriterion("lacation =", value, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationNotEqualTo(String value) {
            addCriterion("lacation <>", value, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationGreaterThan(String value) {
            addCriterion("lacation >", value, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationGreaterThanOrEqualTo(String value) {
            addCriterion("lacation >=", value, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationLessThan(String value) {
            addCriterion("lacation <", value, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationLessThanOrEqualTo(String value) {
            addCriterion("lacation <=", value, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationLike(String value) {
            addCriterion("lacation like", value, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationNotLike(String value) {
            addCriterion("lacation not like", value, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationIn(List<String> values) {
            addCriterion("lacation in", values, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationNotIn(List<String> values) {
            addCriterion("lacation not in", values, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationBetween(String value1, String value2) {
            addCriterion("lacation between", value1, value2, "lacation");
            return (Criteria) this;
        }

        public Criteria andLacationNotBetween(String value1, String value2) {
            addCriterion("lacation not between", value1, value2, "lacation");
            return (Criteria) this;
        }

        public Criteria andDatatimeIsNull() {
            addCriterion("dataTime is null");
            return (Criteria) this;
        }

        public Criteria andDatatimeIsNotNull() {
            addCriterion("dataTime is not null");
            return (Criteria) this;
        }

        public Criteria andDatatimeEqualTo(Date value) {
            addCriterion("dataTime =", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotEqualTo(Date value) {
            addCriterion("dataTime <>", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeGreaterThan(Date value) {
            addCriterion("dataTime >", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeGreaterThanOrEqualTo(Date value) {
            addCriterion("dataTime >=", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLessThan(Date value) {
            addCriterion("dataTime <", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeLessThanOrEqualTo(Date value) {
            addCriterion("dataTime <=", value, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeIn(List<Date> values) {
            addCriterion("dataTime in", values, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotIn(List<Date> values) {
            addCriterion("dataTime not in", values, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeBetween(Date value1, Date value2) {
            addCriterion("dataTime between", value1, value2, "datatime");
            return (Criteria) this;
        }

        public Criteria andDatatimeNotBetween(Date value1, Date value2) {
            addCriterion("dataTime not between", value1, value2, "datatime");
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