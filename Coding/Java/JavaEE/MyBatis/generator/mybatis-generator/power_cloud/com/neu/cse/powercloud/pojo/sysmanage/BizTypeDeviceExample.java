package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.List;

public class BizTypeDeviceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizTypeDeviceExample() {
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

        public Criteria andTypedevicenameIsNull() {
            addCriterion("typeDeviceName is null");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameIsNotNull() {
            addCriterion("typeDeviceName is not null");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameEqualTo(String value) {
            addCriterion("typeDeviceName =", value, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameNotEqualTo(String value) {
            addCriterion("typeDeviceName <>", value, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameGreaterThan(String value) {
            addCriterion("typeDeviceName >", value, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameGreaterThanOrEqualTo(String value) {
            addCriterion("typeDeviceName >=", value, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameLessThan(String value) {
            addCriterion("typeDeviceName <", value, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameLessThanOrEqualTo(String value) {
            addCriterion("typeDeviceName <=", value, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameLike(String value) {
            addCriterion("typeDeviceName like", value, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameNotLike(String value) {
            addCriterion("typeDeviceName not like", value, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameIn(List<String> values) {
            addCriterion("typeDeviceName in", values, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameNotIn(List<String> values) {
            addCriterion("typeDeviceName not in", values, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameBetween(String value1, String value2) {
            addCriterion("typeDeviceName between", value1, value2, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andTypedevicenameNotBetween(String value1, String value2) {
            addCriterion("typeDeviceName not between", value1, value2, "typedevicename");
            return (Criteria) this;
        }

        public Criteria andFunctionnameIsNull() {
            addCriterion("functionName is null");
            return (Criteria) this;
        }

        public Criteria andFunctionnameIsNotNull() {
            addCriterion("functionName is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionnameEqualTo(String value) {
            addCriterion("functionName =", value, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameNotEqualTo(String value) {
            addCriterion("functionName <>", value, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameGreaterThan(String value) {
            addCriterion("functionName >", value, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameGreaterThanOrEqualTo(String value) {
            addCriterion("functionName >=", value, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameLessThan(String value) {
            addCriterion("functionName <", value, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameLessThanOrEqualTo(String value) {
            addCriterion("functionName <=", value, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameLike(String value) {
            addCriterion("functionName like", value, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameNotLike(String value) {
            addCriterion("functionName not like", value, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameIn(List<String> values) {
            addCriterion("functionName in", values, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameNotIn(List<String> values) {
            addCriterion("functionName not in", values, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameBetween(String value1, String value2) {
            addCriterion("functionName between", value1, value2, "functionname");
            return (Criteria) this;
        }

        public Criteria andFunctionnameNotBetween(String value1, String value2) {
            addCriterion("functionName not between", value1, value2, "functionname");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandIsNull() {
            addCriterion("gatewayCommand is null");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandIsNotNull() {
            addCriterion("gatewayCommand is not null");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandEqualTo(String value) {
            addCriterion("gatewayCommand =", value, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandNotEqualTo(String value) {
            addCriterion("gatewayCommand <>", value, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandGreaterThan(String value) {
            addCriterion("gatewayCommand >", value, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandGreaterThanOrEqualTo(String value) {
            addCriterion("gatewayCommand >=", value, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandLessThan(String value) {
            addCriterion("gatewayCommand <", value, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandLessThanOrEqualTo(String value) {
            addCriterion("gatewayCommand <=", value, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandLike(String value) {
            addCriterion("gatewayCommand like", value, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandNotLike(String value) {
            addCriterion("gatewayCommand not like", value, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandIn(List<String> values) {
            addCriterion("gatewayCommand in", values, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandNotIn(List<String> values) {
            addCriterion("gatewayCommand not in", values, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandBetween(String value1, String value2) {
            addCriterion("gatewayCommand between", value1, value2, "gatewaycommand");
            return (Criteria) this;
        }

        public Criteria andGatewaycommandNotBetween(String value1, String value2) {
            addCriterion("gatewayCommand not between", value1, value2, "gatewaycommand");
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