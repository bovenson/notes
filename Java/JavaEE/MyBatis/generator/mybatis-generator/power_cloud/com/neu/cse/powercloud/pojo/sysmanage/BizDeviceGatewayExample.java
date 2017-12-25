package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.List;

public class BizDeviceGatewayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizDeviceGatewayExample() {
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

        public Criteria andGatewaynameIsNull() {
            addCriterion("gatewayName is null");
            return (Criteria) this;
        }

        public Criteria andGatewaynameIsNotNull() {
            addCriterion("gatewayName is not null");
            return (Criteria) this;
        }

        public Criteria andGatewaynameEqualTo(String value) {
            addCriterion("gatewayName =", value, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameNotEqualTo(String value) {
            addCriterion("gatewayName <>", value, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameGreaterThan(String value) {
            addCriterion("gatewayName >", value, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameGreaterThanOrEqualTo(String value) {
            addCriterion("gatewayName >=", value, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameLessThan(String value) {
            addCriterion("gatewayName <", value, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameLessThanOrEqualTo(String value) {
            addCriterion("gatewayName <=", value, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameLike(String value) {
            addCriterion("gatewayName like", value, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameNotLike(String value) {
            addCriterion("gatewayName not like", value, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameIn(List<String> values) {
            addCriterion("gatewayName in", values, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameNotIn(List<String> values) {
            addCriterion("gatewayName not in", values, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameBetween(String value1, String value2) {
            addCriterion("gatewayName between", value1, value2, "gatewayname");
            return (Criteria) this;
        }

        public Criteria andGatewaynameNotBetween(String value1, String value2) {
            addCriterion("gatewayName not between", value1, value2, "gatewayname");
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

        public Criteria andMacIsNull() {
            addCriterion("mac is null");
            return (Criteria) this;
        }

        public Criteria andMacIsNotNull() {
            addCriterion("mac is not null");
            return (Criteria) this;
        }

        public Criteria andMacEqualTo(String value) {
            addCriterion("mac =", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotEqualTo(String value) {
            addCriterion("mac <>", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThan(String value) {
            addCriterion("mac >", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacGreaterThanOrEqualTo(String value) {
            addCriterion("mac >=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThan(String value) {
            addCriterion("mac <", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLessThanOrEqualTo(String value) {
            addCriterion("mac <=", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacLike(String value) {
            addCriterion("mac like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotLike(String value) {
            addCriterion("mac not like", value, "mac");
            return (Criteria) this;
        }

        public Criteria andMacIn(List<String> values) {
            addCriterion("mac in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotIn(List<String> values) {
            addCriterion("mac not in", values, "mac");
            return (Criteria) this;
        }

        public Criteria andMacBetween(String value1, String value2) {
            addCriterion("mac between", value1, value2, "mac");
            return (Criteria) this;
        }

        public Criteria andMacNotBetween(String value1, String value2) {
            addCriterion("mac not between", value1, value2, "mac");
            return (Criteria) this;
        }

        public Criteria andSubjectidIsNull() {
            addCriterion("subjectID is null");
            return (Criteria) this;
        }

        public Criteria andSubjectidIsNotNull() {
            addCriterion("subjectID is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectidEqualTo(String value) {
            addCriterion("subjectID =", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidNotEqualTo(String value) {
            addCriterion("subjectID <>", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidGreaterThan(String value) {
            addCriterion("subjectID >", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidGreaterThanOrEqualTo(String value) {
            addCriterion("subjectID >=", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidLessThan(String value) {
            addCriterion("subjectID <", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidLessThanOrEqualTo(String value) {
            addCriterion("subjectID <=", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidLike(String value) {
            addCriterion("subjectID like", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidNotLike(String value) {
            addCriterion("subjectID not like", value, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidIn(List<String> values) {
            addCriterion("subjectID in", values, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidNotIn(List<String> values) {
            addCriterion("subjectID not in", values, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidBetween(String value1, String value2) {
            addCriterion("subjectID between", value1, value2, "subjectid");
            return (Criteria) this;
        }

        public Criteria andSubjectidNotBetween(String value1, String value2) {
            addCriterion("subjectID not between", value1, value2, "subjectid");
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

        public Criteria andGatewayusrIsNull() {
            addCriterion("gatewayUSR is null");
            return (Criteria) this;
        }

        public Criteria andGatewayusrIsNotNull() {
            addCriterion("gatewayUSR is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayusrEqualTo(String value) {
            addCriterion("gatewayUSR =", value, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrNotEqualTo(String value) {
            addCriterion("gatewayUSR <>", value, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrGreaterThan(String value) {
            addCriterion("gatewayUSR >", value, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrGreaterThanOrEqualTo(String value) {
            addCriterion("gatewayUSR >=", value, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrLessThan(String value) {
            addCriterion("gatewayUSR <", value, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrLessThanOrEqualTo(String value) {
            addCriterion("gatewayUSR <=", value, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrLike(String value) {
            addCriterion("gatewayUSR like", value, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrNotLike(String value) {
            addCriterion("gatewayUSR not like", value, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrIn(List<String> values) {
            addCriterion("gatewayUSR in", values, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrNotIn(List<String> values) {
            addCriterion("gatewayUSR not in", values, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrBetween(String value1, String value2) {
            addCriterion("gatewayUSR between", value1, value2, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewayusrNotBetween(String value1, String value2) {
            addCriterion("gatewayUSR not between", value1, value2, "gatewayusr");
            return (Criteria) this;
        }

        public Criteria andGatewaypswIsNull() {
            addCriterion("gatewayPSW is null");
            return (Criteria) this;
        }

        public Criteria andGatewaypswIsNotNull() {
            addCriterion("gatewayPSW is not null");
            return (Criteria) this;
        }

        public Criteria andGatewaypswEqualTo(String value) {
            addCriterion("gatewayPSW =", value, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswNotEqualTo(String value) {
            addCriterion("gatewayPSW <>", value, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswGreaterThan(String value) {
            addCriterion("gatewayPSW >", value, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswGreaterThanOrEqualTo(String value) {
            addCriterion("gatewayPSW >=", value, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswLessThan(String value) {
            addCriterion("gatewayPSW <", value, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswLessThanOrEqualTo(String value) {
            addCriterion("gatewayPSW <=", value, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswLike(String value) {
            addCriterion("gatewayPSW like", value, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswNotLike(String value) {
            addCriterion("gatewayPSW not like", value, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswIn(List<String> values) {
            addCriterion("gatewayPSW in", values, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswNotIn(List<String> values) {
            addCriterion("gatewayPSW not in", values, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswBetween(String value1, String value2) {
            addCriterion("gatewayPSW between", value1, value2, "gatewaypsw");
            return (Criteria) this;
        }

        public Criteria andGatewaypswNotBetween(String value1, String value2) {
            addCriterion("gatewayPSW not between", value1, value2, "gatewaypsw");
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