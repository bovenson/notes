package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BizElectricitySubstationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizElectricitySubstationExample() {
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

        public Criteria andSubstationnameIsNull() {
            addCriterion("subStationName is null");
            return (Criteria) this;
        }

        public Criteria andSubstationnameIsNotNull() {
            addCriterion("subStationName is not null");
            return (Criteria) this;
        }

        public Criteria andSubstationnameEqualTo(String value) {
            addCriterion("subStationName =", value, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameNotEqualTo(String value) {
            addCriterion("subStationName <>", value, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameGreaterThan(String value) {
            addCriterion("subStationName >", value, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameGreaterThanOrEqualTo(String value) {
            addCriterion("subStationName >=", value, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameLessThan(String value) {
            addCriterion("subStationName <", value, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameLessThanOrEqualTo(String value) {
            addCriterion("subStationName <=", value, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameLike(String value) {
            addCriterion("subStationName like", value, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameNotLike(String value) {
            addCriterion("subStationName not like", value, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameIn(List<String> values) {
            addCriterion("subStationName in", values, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameNotIn(List<String> values) {
            addCriterion("subStationName not in", values, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameBetween(String value1, String value2) {
            addCriterion("subStationName between", value1, value2, "substationname");
            return (Criteria) this;
        }

        public Criteria andSubstationnameNotBetween(String value1, String value2) {
            addCriterion("subStationName not between", value1, value2, "substationname");
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

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(String value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(String value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(String value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(String value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(String value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(String value) {
            addCriterion("longitude like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(String value) {
            addCriterion("longitude not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<String> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<String> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(String value1, String value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(String value1, String value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(String value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(String value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(String value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(String value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(String value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(String value) {
            addCriterion("latitude like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(String value) {
            addCriterion("latitude not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<String> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<String> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(String value1, String value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(String value1, String value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andPowertypeIsNull() {
            addCriterion("powerType is null");
            return (Criteria) this;
        }

        public Criteria andPowertypeIsNotNull() {
            addCriterion("powerType is not null");
            return (Criteria) this;
        }

        public Criteria andPowertypeEqualTo(String value) {
            addCriterion("powerType =", value, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeNotEqualTo(String value) {
            addCriterion("powerType <>", value, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeGreaterThan(String value) {
            addCriterion("powerType >", value, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeGreaterThanOrEqualTo(String value) {
            addCriterion("powerType >=", value, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeLessThan(String value) {
            addCriterion("powerType <", value, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeLessThanOrEqualTo(String value) {
            addCriterion("powerType <=", value, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeLike(String value) {
            addCriterion("powerType like", value, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeNotLike(String value) {
            addCriterion("powerType not like", value, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeIn(List<String> values) {
            addCriterion("powerType in", values, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeNotIn(List<String> values) {
            addCriterion("powerType not in", values, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeBetween(String value1, String value2) {
            addCriterion("powerType between", value1, value2, "powertype");
            return (Criteria) this;
        }

        public Criteria andPowertypeNotBetween(String value1, String value2) {
            addCriterion("powerType not between", value1, value2, "powertype");
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

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andConstructionunitIsNull() {
            addCriterion("ConstructionUnit is null");
            return (Criteria) this;
        }

        public Criteria andConstructionunitIsNotNull() {
            addCriterion("ConstructionUnit is not null");
            return (Criteria) this;
        }

        public Criteria andConstructionunitEqualTo(String value) {
            addCriterion("ConstructionUnit =", value, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitNotEqualTo(String value) {
            addCriterion("ConstructionUnit <>", value, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitGreaterThan(String value) {
            addCriterion("ConstructionUnit >", value, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitGreaterThanOrEqualTo(String value) {
            addCriterion("ConstructionUnit >=", value, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitLessThan(String value) {
            addCriterion("ConstructionUnit <", value, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitLessThanOrEqualTo(String value) {
            addCriterion("ConstructionUnit <=", value, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitLike(String value) {
            addCriterion("ConstructionUnit like", value, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitNotLike(String value) {
            addCriterion("ConstructionUnit not like", value, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitIn(List<String> values) {
            addCriterion("ConstructionUnit in", values, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitNotIn(List<String> values) {
            addCriterion("ConstructionUnit not in", values, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitBetween(String value1, String value2) {
            addCriterion("ConstructionUnit between", value1, value2, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructionunitNotBetween(String value1, String value2) {
            addCriterion("ConstructionUnit not between", value1, value2, "constructionunit");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeIsNull() {
            addCriterion("ConstructionTime is null");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeIsNotNull() {
            addCriterion("ConstructionTime is not null");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeEqualTo(Date value) {
            addCriterionForJDBCDate("ConstructionTime =", value, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("ConstructionTime <>", value, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeGreaterThan(Date value) {
            addCriterionForJDBCDate("ConstructionTime >", value, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ConstructionTime >=", value, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeLessThan(Date value) {
            addCriterionForJDBCDate("ConstructionTime <", value, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ConstructionTime <=", value, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeIn(List<Date> values) {
            addCriterionForJDBCDate("ConstructionTime in", values, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("ConstructionTime not in", values, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ConstructionTime between", value1, value2, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andConstructiontimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ConstructionTime not between", value1, value2, "constructiontime");
            return (Criteria) this;
        }

        public Criteria andInvoltageIsNull() {
            addCriterion("inVoltage is null");
            return (Criteria) this;
        }

        public Criteria andInvoltageIsNotNull() {
            addCriterion("inVoltage is not null");
            return (Criteria) this;
        }

        public Criteria andInvoltageEqualTo(String value) {
            addCriterion("inVoltage =", value, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageNotEqualTo(String value) {
            addCriterion("inVoltage <>", value, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageGreaterThan(String value) {
            addCriterion("inVoltage >", value, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageGreaterThanOrEqualTo(String value) {
            addCriterion("inVoltage >=", value, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageLessThan(String value) {
            addCriterion("inVoltage <", value, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageLessThanOrEqualTo(String value) {
            addCriterion("inVoltage <=", value, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageLike(String value) {
            addCriterion("inVoltage like", value, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageNotLike(String value) {
            addCriterion("inVoltage not like", value, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageIn(List<String> values) {
            addCriterion("inVoltage in", values, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageNotIn(List<String> values) {
            addCriterion("inVoltage not in", values, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageBetween(String value1, String value2) {
            addCriterion("inVoltage between", value1, value2, "involtage");
            return (Criteria) this;
        }

        public Criteria andInvoltageNotBetween(String value1, String value2) {
            addCriterion("inVoltage not between", value1, value2, "involtage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageIsNull() {
            addCriterion("outVoltage is null");
            return (Criteria) this;
        }

        public Criteria andOutvoltageIsNotNull() {
            addCriterion("outVoltage is not null");
            return (Criteria) this;
        }

        public Criteria andOutvoltageEqualTo(String value) {
            addCriterion("outVoltage =", value, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageNotEqualTo(String value) {
            addCriterion("outVoltage <>", value, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageGreaterThan(String value) {
            addCriterion("outVoltage >", value, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageGreaterThanOrEqualTo(String value) {
            addCriterion("outVoltage >=", value, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageLessThan(String value) {
            addCriterion("outVoltage <", value, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageLessThanOrEqualTo(String value) {
            addCriterion("outVoltage <=", value, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageLike(String value) {
            addCriterion("outVoltage like", value, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageNotLike(String value) {
            addCriterion("outVoltage not like", value, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageIn(List<String> values) {
            addCriterion("outVoltage in", values, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageNotIn(List<String> values) {
            addCriterion("outVoltage not in", values, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageBetween(String value1, String value2) {
            addCriterion("outVoltage between", value1, value2, "outvoltage");
            return (Criteria) this;
        }

        public Criteria andOutvoltageNotBetween(String value1, String value2) {
            addCriterion("outVoltage not between", value1, value2, "outvoltage");
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