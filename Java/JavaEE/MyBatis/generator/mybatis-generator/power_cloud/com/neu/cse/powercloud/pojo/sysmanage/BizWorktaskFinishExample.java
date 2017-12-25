package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.List;

public class BizWorktaskFinishExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizWorktaskFinishExample() {
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andProcessactionIsNull() {
            addCriterion("processAction is null");
            return (Criteria) this;
        }

        public Criteria andProcessactionIsNotNull() {
            addCriterion("processAction is not null");
            return (Criteria) this;
        }

        public Criteria andProcessactionEqualTo(String value) {
            addCriterion("processAction =", value, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionNotEqualTo(String value) {
            addCriterion("processAction <>", value, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionGreaterThan(String value) {
            addCriterion("processAction >", value, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionGreaterThanOrEqualTo(String value) {
            addCriterion("processAction >=", value, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionLessThan(String value) {
            addCriterion("processAction <", value, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionLessThanOrEqualTo(String value) {
            addCriterion("processAction <=", value, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionLike(String value) {
            addCriterion("processAction like", value, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionNotLike(String value) {
            addCriterion("processAction not like", value, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionIn(List<String> values) {
            addCriterion("processAction in", values, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionNotIn(List<String> values) {
            addCriterion("processAction not in", values, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionBetween(String value1, String value2) {
            addCriterion("processAction between", value1, value2, "processaction");
            return (Criteria) this;
        }

        public Criteria andProcessactionNotBetween(String value1, String value2) {
            addCriterion("processAction not between", value1, value2, "processaction");
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

        public Criteria andCompanyidIsNull() {
            addCriterion("companyID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNotNull() {
            addCriterion("companyID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyidEqualTo(Integer value) {
            addCriterion("companyID =", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotEqualTo(Integer value) {
            addCriterion("companyID <>", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThan(Integer value) {
            addCriterion("companyID >", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("companyID >=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThan(Integer value) {
            addCriterion("companyID <", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThanOrEqualTo(Integer value) {
            addCriterion("companyID <=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidIn(List<Integer> values) {
            addCriterion("companyID in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotIn(List<Integer> values) {
            addCriterion("companyID not in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidBetween(Integer value1, Integer value2) {
            addCriterion("companyID between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotBetween(Integer value1, Integer value2) {
            addCriterion("companyID not between", value1, value2, "companyid");
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

        public Criteria andCommenterIsNull() {
            addCriterion("commenter is null");
            return (Criteria) this;
        }

        public Criteria andCommenterIsNotNull() {
            addCriterion("commenter is not null");
            return (Criteria) this;
        }

        public Criteria andCommenterEqualTo(Integer value) {
            addCriterion("commenter =", value, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterNotEqualTo(Integer value) {
            addCriterion("commenter <>", value, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterGreaterThan(Integer value) {
            addCriterion("commenter >", value, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterGreaterThanOrEqualTo(Integer value) {
            addCriterion("commenter >=", value, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterLessThan(Integer value) {
            addCriterion("commenter <", value, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterLessThanOrEqualTo(Integer value) {
            addCriterion("commenter <=", value, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterIn(List<Integer> values) {
            addCriterion("commenter in", values, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterNotIn(List<Integer> values) {
            addCriterion("commenter not in", values, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterBetween(Integer value1, Integer value2) {
            addCriterion("commenter between", value1, value2, "commenter");
            return (Criteria) this;
        }

        public Criteria andCommenterNotBetween(Integer value1, Integer value2) {
            addCriterion("commenter not between", value1, value2, "commenter");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(String value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(String value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(String value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(String value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(String value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(String value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLike(String value) {
            addCriterion("score like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotLike(String value) {
            addCriterion("score not like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<String> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<String> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(String value1, String value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(String value1, String value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
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