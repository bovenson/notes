package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BizElectricianExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizElectricianExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andIdentitycardIsNull() {
            addCriterion("identityCard is null");
            return (Criteria) this;
        }

        public Criteria andIdentitycardIsNotNull() {
            addCriterion("identityCard is not null");
            return (Criteria) this;
        }

        public Criteria andIdentitycardEqualTo(String value) {
            addCriterion("identityCard =", value, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardNotEqualTo(String value) {
            addCriterion("identityCard <>", value, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardGreaterThan(String value) {
            addCriterion("identityCard >", value, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardGreaterThanOrEqualTo(String value) {
            addCriterion("identityCard >=", value, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardLessThan(String value) {
            addCriterion("identityCard <", value, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardLessThanOrEqualTo(String value) {
            addCriterion("identityCard <=", value, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardLike(String value) {
            addCriterion("identityCard like", value, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardNotLike(String value) {
            addCriterion("identityCard not like", value, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardIn(List<String> values) {
            addCriterion("identityCard in", values, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardNotIn(List<String> values) {
            addCriterion("identityCard not in", values, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardBetween(String value1, String value2) {
            addCriterion("identityCard between", value1, value2, "identitycard");
            return (Criteria) this;
        }

        public Criteria andIdentitycardNotBetween(String value1, String value2) {
            addCriterion("identityCard not between", value1, value2, "identitycard");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("Tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("Tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("Tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("Tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("Tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("Tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("Tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("Tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("Tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("Tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("Tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("Tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("Tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("Tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleIsNull() {
            addCriterion("positionalTitle is null");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleIsNotNull() {
            addCriterion("positionalTitle is not null");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleEqualTo(String value) {
            addCriterion("positionalTitle =", value, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleNotEqualTo(String value) {
            addCriterion("positionalTitle <>", value, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleGreaterThan(String value) {
            addCriterion("positionalTitle >", value, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleGreaterThanOrEqualTo(String value) {
            addCriterion("positionalTitle >=", value, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleLessThan(String value) {
            addCriterion("positionalTitle <", value, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleLessThanOrEqualTo(String value) {
            addCriterion("positionalTitle <=", value, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleLike(String value) {
            addCriterion("positionalTitle like", value, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleNotLike(String value) {
            addCriterion("positionalTitle not like", value, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleIn(List<String> values) {
            addCriterion("positionalTitle in", values, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleNotIn(List<String> values) {
            addCriterion("positionalTitle not in", values, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleBetween(String value1, String value2) {
            addCriterion("positionalTitle between", value1, value2, "positionaltitle");
            return (Criteria) this;
        }

        public Criteria andPositionaltitleNotBetween(String value1, String value2) {
            addCriterion("positionalTitle not between", value1, value2, "positionaltitle");
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

        public Criteria andEcnumIsNull() {
            addCriterion("ecNum is null");
            return (Criteria) this;
        }

        public Criteria andEcnumIsNotNull() {
            addCriterion("ecNum is not null");
            return (Criteria) this;
        }

        public Criteria andEcnumEqualTo(String value) {
            addCriterion("ecNum =", value, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumNotEqualTo(String value) {
            addCriterion("ecNum <>", value, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumGreaterThan(String value) {
            addCriterion("ecNum >", value, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumGreaterThanOrEqualTo(String value) {
            addCriterion("ecNum >=", value, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumLessThan(String value) {
            addCriterion("ecNum <", value, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumLessThanOrEqualTo(String value) {
            addCriterion("ecNum <=", value, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumLike(String value) {
            addCriterion("ecNum like", value, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumNotLike(String value) {
            addCriterion("ecNum not like", value, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumIn(List<String> values) {
            addCriterion("ecNum in", values, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumNotIn(List<String> values) {
            addCriterion("ecNum not in", values, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumBetween(String value1, String value2) {
            addCriterion("ecNum between", value1, value2, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEcnumNotBetween(String value1, String value2) {
            addCriterion("ecNum not between", value1, value2, "ecnum");
            return (Criteria) this;
        }

        public Criteria andEctypeIsNull() {
            addCriterion("ecType is null");
            return (Criteria) this;
        }

        public Criteria andEctypeIsNotNull() {
            addCriterion("ecType is not null");
            return (Criteria) this;
        }

        public Criteria andEctypeEqualTo(String value) {
            addCriterion("ecType =", value, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeNotEqualTo(String value) {
            addCriterion("ecType <>", value, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeGreaterThan(String value) {
            addCriterion("ecType >", value, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeGreaterThanOrEqualTo(String value) {
            addCriterion("ecType >=", value, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeLessThan(String value) {
            addCriterion("ecType <", value, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeLessThanOrEqualTo(String value) {
            addCriterion("ecType <=", value, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeLike(String value) {
            addCriterion("ecType like", value, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeNotLike(String value) {
            addCriterion("ecType not like", value, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeIn(List<String> values) {
            addCriterion("ecType in", values, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeNotIn(List<String> values) {
            addCriterion("ecType not in", values, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeBetween(String value1, String value2) {
            addCriterion("ecType between", value1, value2, "ectype");
            return (Criteria) this;
        }

        public Criteria andEctypeNotBetween(String value1, String value2) {
            addCriterion("ecType not between", value1, value2, "ectype");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeIsNull() {
            addCriterion("ecGrantTime is null");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeIsNotNull() {
            addCriterion("ecGrantTime is not null");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeEqualTo(Date value) {
            addCriterionForJDBCDate("ecGrantTime =", value, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("ecGrantTime <>", value, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("ecGrantTime >", value, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ecGrantTime >=", value, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeLessThan(Date value) {
            addCriterionForJDBCDate("ecGrantTime <", value, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ecGrantTime <=", value, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeIn(List<Date> values) {
            addCriterionForJDBCDate("ecGrantTime in", values, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("ecGrantTime not in", values, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ecGrantTime between", value1, value2, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEcgranttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ecGrantTime not between", value1, value2, "ecgranttime");
            return (Criteria) this;
        }

        public Criteria andEclicensenumIsNull() {
            addCriterion("ecLicenseNum is null");
            return (Criteria) this;
        }

        public Criteria andEclicensenumIsNotNull() {
            addCriterion("ecLicenseNum is not null");
            return (Criteria) this;
        }

        public Criteria andEclicensenumEqualTo(String value) {
            addCriterion("ecLicenseNum =", value, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumNotEqualTo(String value) {
            addCriterion("ecLicenseNum <>", value, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumGreaterThan(String value) {
            addCriterion("ecLicenseNum >", value, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumGreaterThanOrEqualTo(String value) {
            addCriterion("ecLicenseNum >=", value, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumLessThan(String value) {
            addCriterion("ecLicenseNum <", value, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumLessThanOrEqualTo(String value) {
            addCriterion("ecLicenseNum <=", value, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumLike(String value) {
            addCriterion("ecLicenseNum like", value, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumNotLike(String value) {
            addCriterion("ecLicenseNum not like", value, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumIn(List<String> values) {
            addCriterion("ecLicenseNum in", values, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumNotIn(List<String> values) {
            addCriterion("ecLicenseNum not in", values, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumBetween(String value1, String value2) {
            addCriterion("ecLicenseNum between", value1, value2, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEclicensenumNotBetween(String value1, String value2) {
            addCriterion("ecLicenseNum not between", value1, value2, "eclicensenum");
            return (Criteria) this;
        }

        public Criteria andEcpicIsNull() {
            addCriterion("ecPic is null");
            return (Criteria) this;
        }

        public Criteria andEcpicIsNotNull() {
            addCriterion("ecPic is not null");
            return (Criteria) this;
        }

        public Criteria andEcpicEqualTo(String value) {
            addCriterion("ecPic =", value, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicNotEqualTo(String value) {
            addCriterion("ecPic <>", value, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicGreaterThan(String value) {
            addCriterion("ecPic >", value, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicGreaterThanOrEqualTo(String value) {
            addCriterion("ecPic >=", value, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicLessThan(String value) {
            addCriterion("ecPic <", value, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicLessThanOrEqualTo(String value) {
            addCriterion("ecPic <=", value, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicLike(String value) {
            addCriterion("ecPic like", value, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicNotLike(String value) {
            addCriterion("ecPic not like", value, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicIn(List<String> values) {
            addCriterion("ecPic in", values, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicNotIn(List<String> values) {
            addCriterion("ecPic not in", values, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicBetween(String value1, String value2) {
            addCriterion("ecPic between", value1, value2, "ecpic");
            return (Criteria) this;
        }

        public Criteria andEcpicNotBetween(String value1, String value2) {
            addCriterion("ecPic not between", value1, value2, "ecpic");
            return (Criteria) this;
        }

        public Criteria andScnumIsNull() {
            addCriterion("scNum is null");
            return (Criteria) this;
        }

        public Criteria andScnumIsNotNull() {
            addCriterion("scNum is not null");
            return (Criteria) this;
        }

        public Criteria andScnumEqualTo(String value) {
            addCriterion("scNum =", value, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumNotEqualTo(String value) {
            addCriterion("scNum <>", value, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumGreaterThan(String value) {
            addCriterion("scNum >", value, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumGreaterThanOrEqualTo(String value) {
            addCriterion("scNum >=", value, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumLessThan(String value) {
            addCriterion("scNum <", value, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumLessThanOrEqualTo(String value) {
            addCriterion("scNum <=", value, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumLike(String value) {
            addCriterion("scNum like", value, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumNotLike(String value) {
            addCriterion("scNum not like", value, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumIn(List<String> values) {
            addCriterion("scNum in", values, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumNotIn(List<String> values) {
            addCriterion("scNum not in", values, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumBetween(String value1, String value2) {
            addCriterion("scNum between", value1, value2, "scnum");
            return (Criteria) this;
        }

        public Criteria andScnumNotBetween(String value1, String value2) {
            addCriterion("scNum not between", value1, value2, "scnum");
            return (Criteria) this;
        }

        public Criteria andSccompanynameIsNull() {
            addCriterion("scCompanyName is null");
            return (Criteria) this;
        }

        public Criteria andSccompanynameIsNotNull() {
            addCriterion("scCompanyName is not null");
            return (Criteria) this;
        }

        public Criteria andSccompanynameEqualTo(String value) {
            addCriterion("scCompanyName =", value, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameNotEqualTo(String value) {
            addCriterion("scCompanyName <>", value, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameGreaterThan(String value) {
            addCriterion("scCompanyName >", value, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameGreaterThanOrEqualTo(String value) {
            addCriterion("scCompanyName >=", value, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameLessThan(String value) {
            addCriterion("scCompanyName <", value, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameLessThanOrEqualTo(String value) {
            addCriterion("scCompanyName <=", value, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameLike(String value) {
            addCriterion("scCompanyName like", value, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameNotLike(String value) {
            addCriterion("scCompanyName not like", value, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameIn(List<String> values) {
            addCriterion("scCompanyName in", values, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameNotIn(List<String> values) {
            addCriterion("scCompanyName not in", values, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameBetween(String value1, String value2) {
            addCriterion("scCompanyName between", value1, value2, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andSccompanynameNotBetween(String value1, String value2) {
            addCriterion("scCompanyName not between", value1, value2, "sccompanyname");
            return (Criteria) this;
        }

        public Criteria andScdutyIsNull() {
            addCriterion("scDuty is null");
            return (Criteria) this;
        }

        public Criteria andScdutyIsNotNull() {
            addCriterion("scDuty is not null");
            return (Criteria) this;
        }

        public Criteria andScdutyEqualTo(String value) {
            addCriterion("scDuty =", value, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyNotEqualTo(String value) {
            addCriterion("scDuty <>", value, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyGreaterThan(String value) {
            addCriterion("scDuty >", value, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyGreaterThanOrEqualTo(String value) {
            addCriterion("scDuty >=", value, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyLessThan(String value) {
            addCriterion("scDuty <", value, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyLessThanOrEqualTo(String value) {
            addCriterion("scDuty <=", value, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyLike(String value) {
            addCriterion("scDuty like", value, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyNotLike(String value) {
            addCriterion("scDuty not like", value, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyIn(List<String> values) {
            addCriterion("scDuty in", values, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyNotIn(List<String> values) {
            addCriterion("scDuty not in", values, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyBetween(String value1, String value2) {
            addCriterion("scDuty between", value1, value2, "scduty");
            return (Criteria) this;
        }

        public Criteria andScdutyNotBetween(String value1, String value2) {
            addCriterion("scDuty not between", value1, value2, "scduty");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleIsNull() {
            addCriterion("scTechnicalTitle is null");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleIsNotNull() {
            addCriterion("scTechnicalTitle is not null");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleEqualTo(String value) {
            addCriterion("scTechnicalTitle =", value, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleNotEqualTo(String value) {
            addCriterion("scTechnicalTitle <>", value, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleGreaterThan(String value) {
            addCriterion("scTechnicalTitle >", value, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleGreaterThanOrEqualTo(String value) {
            addCriterion("scTechnicalTitle >=", value, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleLessThan(String value) {
            addCriterion("scTechnicalTitle <", value, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleLessThanOrEqualTo(String value) {
            addCriterion("scTechnicalTitle <=", value, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleLike(String value) {
            addCriterion("scTechnicalTitle like", value, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleNotLike(String value) {
            addCriterion("scTechnicalTitle not like", value, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleIn(List<String> values) {
            addCriterion("scTechnicalTitle in", values, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleNotIn(List<String> values) {
            addCriterion("scTechnicalTitle not in", values, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleBetween(String value1, String value2) {
            addCriterion("scTechnicalTitle between", value1, value2, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andSctechnicaltitleNotBetween(String value1, String value2) {
            addCriterion("scTechnicalTitle not between", value1, value2, "sctechnicaltitle");
            return (Criteria) this;
        }

        public Criteria andScissuedateIsNull() {
            addCriterion("scIssueDate is null");
            return (Criteria) this;
        }

        public Criteria andScissuedateIsNotNull() {
            addCriterion("scIssueDate is not null");
            return (Criteria) this;
        }

        public Criteria andScissuedateEqualTo(Date value) {
            addCriterionForJDBCDate("scIssueDate =", value, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("scIssueDate <>", value, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateGreaterThan(Date value) {
            addCriterionForJDBCDate("scIssueDate >", value, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("scIssueDate >=", value, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateLessThan(Date value) {
            addCriterionForJDBCDate("scIssueDate <", value, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("scIssueDate <=", value, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateIn(List<Date> values) {
            addCriterionForJDBCDate("scIssueDate in", values, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("scIssueDate not in", values, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("scIssueDate between", value1, value2, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScissuedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("scIssueDate not between", value1, value2, "scissuedate");
            return (Criteria) this;
        }

        public Criteria andScdatestartIsNull() {
            addCriterion("scDateStart is null");
            return (Criteria) this;
        }

        public Criteria andScdatestartIsNotNull() {
            addCriterion("scDateStart is not null");
            return (Criteria) this;
        }

        public Criteria andScdatestartEqualTo(Date value) {
            addCriterionForJDBCDate("scDateStart =", value, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartNotEqualTo(Date value) {
            addCriterionForJDBCDate("scDateStart <>", value, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartGreaterThan(Date value) {
            addCriterionForJDBCDate("scDateStart >", value, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("scDateStart >=", value, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartLessThan(Date value) {
            addCriterionForJDBCDate("scDateStart <", value, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("scDateStart <=", value, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartIn(List<Date> values) {
            addCriterionForJDBCDate("scDateStart in", values, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartNotIn(List<Date> values) {
            addCriterionForJDBCDate("scDateStart not in", values, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("scDateStart between", value1, value2, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdatestartNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("scDateStart not between", value1, value2, "scdatestart");
            return (Criteria) this;
        }

        public Criteria andScdateendIsNull() {
            addCriterion("scDateEnd is null");
            return (Criteria) this;
        }

        public Criteria andScdateendIsNotNull() {
            addCriterion("scDateEnd is not null");
            return (Criteria) this;
        }

        public Criteria andScdateendEqualTo(Date value) {
            addCriterionForJDBCDate("scDateEnd =", value, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendNotEqualTo(Date value) {
            addCriterionForJDBCDate("scDateEnd <>", value, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendGreaterThan(Date value) {
            addCriterionForJDBCDate("scDateEnd >", value, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("scDateEnd >=", value, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendLessThan(Date value) {
            addCriterionForJDBCDate("scDateEnd <", value, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("scDateEnd <=", value, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendIn(List<Date> values) {
            addCriterionForJDBCDate("scDateEnd in", values, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendNotIn(List<Date> values) {
            addCriterionForJDBCDate("scDateEnd not in", values, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("scDateEnd between", value1, value2, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScdateendNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("scDateEnd not between", value1, value2, "scdateend");
            return (Criteria) this;
        }

        public Criteria andScpicIsNull() {
            addCriterion("scPic is null");
            return (Criteria) this;
        }

        public Criteria andScpicIsNotNull() {
            addCriterion("scPic is not null");
            return (Criteria) this;
        }

        public Criteria andScpicEqualTo(String value) {
            addCriterion("scPic =", value, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicNotEqualTo(String value) {
            addCriterion("scPic <>", value, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicGreaterThan(String value) {
            addCriterion("scPic >", value, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicGreaterThanOrEqualTo(String value) {
            addCriterion("scPic >=", value, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicLessThan(String value) {
            addCriterion("scPic <", value, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicLessThanOrEqualTo(String value) {
            addCriterion("scPic <=", value, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicLike(String value) {
            addCriterion("scPic like", value, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicNotLike(String value) {
            addCriterion("scPic not like", value, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicIn(List<String> values) {
            addCriterion("scPic in", values, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicNotIn(List<String> values) {
            addCriterion("scPic not in", values, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicBetween(String value1, String value2) {
            addCriterion("scPic between", value1, value2, "scpic");
            return (Criteria) this;
        }

        public Criteria andScpicNotBetween(String value1, String value2) {
            addCriterion("scPic not between", value1, value2, "scpic");
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

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
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