package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizCustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BizCustomerExample() {
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

        public Criteria andShortnameIsNull() {
            addCriterion("shortName is null");
            return (Criteria) this;
        }

        public Criteria andShortnameIsNotNull() {
            addCriterion("shortName is not null");
            return (Criteria) this;
        }

        public Criteria andShortnameEqualTo(String value) {
            addCriterion("shortName =", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotEqualTo(String value) {
            addCriterion("shortName <>", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameGreaterThan(String value) {
            addCriterion("shortName >", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameGreaterThanOrEqualTo(String value) {
            addCriterion("shortName >=", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLessThan(String value) {
            addCriterion("shortName <", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLessThanOrEqualTo(String value) {
            addCriterion("shortName <=", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameLike(String value) {
            addCriterion("shortName like", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotLike(String value) {
            addCriterion("shortName not like", value, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameIn(List<String> values) {
            addCriterion("shortName in", values, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotIn(List<String> values) {
            addCriterion("shortName not in", values, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameBetween(String value1, String value2) {
            addCriterion("shortName between", value1, value2, "shortname");
            return (Criteria) this;
        }

        public Criteria andShortnameNotBetween(String value1, String value2) {
            addCriterion("shortName not between", value1, value2, "shortname");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanycodeIsNull() {
            addCriterion("companyCode is null");
            return (Criteria) this;
        }

        public Criteria andCompanycodeIsNotNull() {
            addCriterion("companyCode is not null");
            return (Criteria) this;
        }

        public Criteria andCompanycodeEqualTo(String value) {
            addCriterion("companyCode =", value, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeNotEqualTo(String value) {
            addCriterion("companyCode <>", value, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeGreaterThan(String value) {
            addCriterion("companyCode >", value, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeGreaterThanOrEqualTo(String value) {
            addCriterion("companyCode >=", value, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeLessThan(String value) {
            addCriterion("companyCode <", value, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeLessThanOrEqualTo(String value) {
            addCriterion("companyCode <=", value, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeLike(String value) {
            addCriterion("companyCode like", value, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeNotLike(String value) {
            addCriterion("companyCode not like", value, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeIn(List<String> values) {
            addCriterion("companyCode in", values, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeNotIn(List<String> values) {
            addCriterion("companyCode not in", values, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeBetween(String value1, String value2) {
            addCriterion("companyCode between", value1, value2, "companycode");
            return (Criteria) this;
        }

        public Criteria andCompanycodeNotBetween(String value1, String value2) {
            addCriterion("companyCode not between", value1, value2, "companycode");
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

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andBizownernameIsNull() {
            addCriterion("bizOwnerName is null");
            return (Criteria) this;
        }

        public Criteria andBizownernameIsNotNull() {
            addCriterion("bizOwnerName is not null");
            return (Criteria) this;
        }

        public Criteria andBizownernameEqualTo(String value) {
            addCriterion("bizOwnerName =", value, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameNotEqualTo(String value) {
            addCriterion("bizOwnerName <>", value, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameGreaterThan(String value) {
            addCriterion("bizOwnerName >", value, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameGreaterThanOrEqualTo(String value) {
            addCriterion("bizOwnerName >=", value, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameLessThan(String value) {
            addCriterion("bizOwnerName <", value, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameLessThanOrEqualTo(String value) {
            addCriterion("bizOwnerName <=", value, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameLike(String value) {
            addCriterion("bizOwnerName like", value, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameNotLike(String value) {
            addCriterion("bizOwnerName not like", value, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameIn(List<String> values) {
            addCriterion("bizOwnerName in", values, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameNotIn(List<String> values) {
            addCriterion("bizOwnerName not in", values, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameBetween(String value1, String value2) {
            addCriterion("bizOwnerName between", value1, value2, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownernameNotBetween(String value1, String value2) {
            addCriterion("bizOwnerName not between", value1, value2, "bizownername");
            return (Criteria) this;
        }

        public Criteria andBizownertelIsNull() {
            addCriterion("bizOwnerTel is null");
            return (Criteria) this;
        }

        public Criteria andBizownertelIsNotNull() {
            addCriterion("bizOwnerTel is not null");
            return (Criteria) this;
        }

        public Criteria andBizownertelEqualTo(String value) {
            addCriterion("bizOwnerTel =", value, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelNotEqualTo(String value) {
            addCriterion("bizOwnerTel <>", value, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelGreaterThan(String value) {
            addCriterion("bizOwnerTel >", value, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelGreaterThanOrEqualTo(String value) {
            addCriterion("bizOwnerTel >=", value, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelLessThan(String value) {
            addCriterion("bizOwnerTel <", value, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelLessThanOrEqualTo(String value) {
            addCriterion("bizOwnerTel <=", value, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelLike(String value) {
            addCriterion("bizOwnerTel like", value, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelNotLike(String value) {
            addCriterion("bizOwnerTel not like", value, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelIn(List<String> values) {
            addCriterion("bizOwnerTel in", values, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelNotIn(List<String> values) {
            addCriterion("bizOwnerTel not in", values, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelBetween(String value1, String value2) {
            addCriterion("bizOwnerTel between", value1, value2, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownertelNotBetween(String value1, String value2) {
            addCriterion("bizOwnerTel not between", value1, value2, "bizownertel");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneIsNull() {
            addCriterion("bizOwnerPhone is null");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneIsNotNull() {
            addCriterion("bizOwnerPhone is not null");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneEqualTo(String value) {
            addCriterion("bizOwnerPhone =", value, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneNotEqualTo(String value) {
            addCriterion("bizOwnerPhone <>", value, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneGreaterThan(String value) {
            addCriterion("bizOwnerPhone >", value, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneGreaterThanOrEqualTo(String value) {
            addCriterion("bizOwnerPhone >=", value, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneLessThan(String value) {
            addCriterion("bizOwnerPhone <", value, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneLessThanOrEqualTo(String value) {
            addCriterion("bizOwnerPhone <=", value, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneLike(String value) {
            addCriterion("bizOwnerPhone like", value, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneNotLike(String value) {
            addCriterion("bizOwnerPhone not like", value, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneIn(List<String> values) {
            addCriterion("bizOwnerPhone in", values, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneNotIn(List<String> values) {
            addCriterion("bizOwnerPhone not in", values, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneBetween(String value1, String value2) {
            addCriterion("bizOwnerPhone between", value1, value2, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andBizownerphoneNotBetween(String value1, String value2) {
            addCriterion("bizOwnerPhone not between", value1, value2, "bizownerphone");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationIsNull() {
            addCriterion("taxpayerIdentification is null");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationIsNotNull() {
            addCriterion("taxpayerIdentification is not null");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationEqualTo(String value) {
            addCriterion("taxpayerIdentification =", value, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationNotEqualTo(String value) {
            addCriterion("taxpayerIdentification <>", value, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationGreaterThan(String value) {
            addCriterion("taxpayerIdentification >", value, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationGreaterThanOrEqualTo(String value) {
            addCriterion("taxpayerIdentification >=", value, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationLessThan(String value) {
            addCriterion("taxpayerIdentification <", value, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationLessThanOrEqualTo(String value) {
            addCriterion("taxpayerIdentification <=", value, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationLike(String value) {
            addCriterion("taxpayerIdentification like", value, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationNotLike(String value) {
            addCriterion("taxpayerIdentification not like", value, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationIn(List<String> values) {
            addCriterion("taxpayerIdentification in", values, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationNotIn(List<String> values) {
            addCriterion("taxpayerIdentification not in", values, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationBetween(String value1, String value2) {
            addCriterion("taxpayerIdentification between", value1, value2, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andTaxpayeridentificationNotBetween(String value1, String value2) {
            addCriterion("taxpayerIdentification not between", value1, value2, "taxpayeridentification");
            return (Criteria) this;
        }

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankaccountIsNull() {
            addCriterion("bankAccount is null");
            return (Criteria) this;
        }

        public Criteria andBankaccountIsNotNull() {
            addCriterion("bankAccount is not null");
            return (Criteria) this;
        }

        public Criteria andBankaccountEqualTo(String value) {
            addCriterion("bankAccount =", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotEqualTo(String value) {
            addCriterion("bankAccount <>", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountGreaterThan(String value) {
            addCriterion("bankAccount >", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountGreaterThanOrEqualTo(String value) {
            addCriterion("bankAccount >=", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLessThan(String value) {
            addCriterion("bankAccount <", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLessThanOrEqualTo(String value) {
            addCriterion("bankAccount <=", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountLike(String value) {
            addCriterion("bankAccount like", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotLike(String value) {
            addCriterion("bankAccount not like", value, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountIn(List<String> values) {
            addCriterion("bankAccount in", values, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotIn(List<String> values) {
            addCriterion("bankAccount not in", values, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountBetween(String value1, String value2) {
            addCriterion("bankAccount between", value1, value2, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andBankaccountNotBetween(String value1, String value2) {
            addCriterion("bankAccount not between", value1, value2, "bankaccount");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameIsNull() {
            addCriterion("financeChiefName is null");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameIsNotNull() {
            addCriterion("financeChiefName is not null");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameEqualTo(String value) {
            addCriterion("financeChiefName =", value, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameNotEqualTo(String value) {
            addCriterion("financeChiefName <>", value, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameGreaterThan(String value) {
            addCriterion("financeChiefName >", value, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameGreaterThanOrEqualTo(String value) {
            addCriterion("financeChiefName >=", value, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameLessThan(String value) {
            addCriterion("financeChiefName <", value, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameLessThanOrEqualTo(String value) {
            addCriterion("financeChiefName <=", value, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameLike(String value) {
            addCriterion("financeChiefName like", value, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameNotLike(String value) {
            addCriterion("financeChiefName not like", value, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameIn(List<String> values) {
            addCriterion("financeChiefName in", values, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameNotIn(List<String> values) {
            addCriterion("financeChiefName not in", values, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameBetween(String value1, String value2) {
            addCriterion("financeChiefName between", value1, value2, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechiefnameNotBetween(String value1, String value2) {
            addCriterion("financeChiefName not between", value1, value2, "financechiefname");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelIsNull() {
            addCriterion("financeChiefTel is null");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelIsNotNull() {
            addCriterion("financeChiefTel is not null");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelEqualTo(String value) {
            addCriterion("financeChiefTel =", value, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelNotEqualTo(String value) {
            addCriterion("financeChiefTel <>", value, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelGreaterThan(String value) {
            addCriterion("financeChiefTel >", value, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelGreaterThanOrEqualTo(String value) {
            addCriterion("financeChiefTel >=", value, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelLessThan(String value) {
            addCriterion("financeChiefTel <", value, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelLessThanOrEqualTo(String value) {
            addCriterion("financeChiefTel <=", value, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelLike(String value) {
            addCriterion("financeChiefTel like", value, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelNotLike(String value) {
            addCriterion("financeChiefTel not like", value, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelIn(List<String> values) {
            addCriterion("financeChiefTel in", values, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelNotIn(List<String> values) {
            addCriterion("financeChiefTel not in", values, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelBetween(String value1, String value2) {
            addCriterion("financeChiefTel between", value1, value2, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechieftelNotBetween(String value1, String value2) {
            addCriterion("financeChiefTel not between", value1, value2, "financechieftel");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneIsNull() {
            addCriterion("financeChiefPhone is null");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneIsNotNull() {
            addCriterion("financeChiefPhone is not null");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneEqualTo(String value) {
            addCriterion("financeChiefPhone =", value, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneNotEqualTo(String value) {
            addCriterion("financeChiefPhone <>", value, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneGreaterThan(String value) {
            addCriterion("financeChiefPhone >", value, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneGreaterThanOrEqualTo(String value) {
            addCriterion("financeChiefPhone >=", value, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneLessThan(String value) {
            addCriterion("financeChiefPhone <", value, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneLessThanOrEqualTo(String value) {
            addCriterion("financeChiefPhone <=", value, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneLike(String value) {
            addCriterion("financeChiefPhone like", value, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneNotLike(String value) {
            addCriterion("financeChiefPhone not like", value, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneIn(List<String> values) {
            addCriterion("financeChiefPhone in", values, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneNotIn(List<String> values) {
            addCriterion("financeChiefPhone not in", values, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneBetween(String value1, String value2) {
            addCriterion("financeChiefPhone between", value1, value2, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andFinancechiefphoneNotBetween(String value1, String value2) {
            addCriterion("financeChiefPhone not between", value1, value2, "financechiefphone");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeIsNull() {
            addCriterion("companyTypeCode is null");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeIsNotNull() {
            addCriterion("companyTypeCode is not null");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeEqualTo(String value) {
            addCriterion("companyTypeCode =", value, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeNotEqualTo(String value) {
            addCriterion("companyTypeCode <>", value, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeGreaterThan(String value) {
            addCriterion("companyTypeCode >", value, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeGreaterThanOrEqualTo(String value) {
            addCriterion("companyTypeCode >=", value, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeLessThan(String value) {
            addCriterion("companyTypeCode <", value, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeLessThanOrEqualTo(String value) {
            addCriterion("companyTypeCode <=", value, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeLike(String value) {
            addCriterion("companyTypeCode like", value, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeNotLike(String value) {
            addCriterion("companyTypeCode not like", value, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeIn(List<String> values) {
            addCriterion("companyTypeCode in", values, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeNotIn(List<String> values) {
            addCriterion("companyTypeCode not in", values, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeBetween(String value1, String value2) {
            addCriterion("companyTypeCode between", value1, value2, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andCompanytypecodeNotBetween(String value1, String value2) {
            addCriterion("companyTypeCode not between", value1, value2, "companytypecode");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidIsNull() {
            addCriterion("dataAuthorityID is null");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidIsNotNull() {
            addCriterion("dataAuthorityID is not null");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidEqualTo(Integer value) {
            addCriterion("dataAuthorityID =", value, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidNotEqualTo(Integer value) {
            addCriterion("dataAuthorityID <>", value, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidGreaterThan(Integer value) {
            addCriterion("dataAuthorityID >", value, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("dataAuthorityID >=", value, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidLessThan(Integer value) {
            addCriterion("dataAuthorityID <", value, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidLessThanOrEqualTo(Integer value) {
            addCriterion("dataAuthorityID <=", value, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidIn(List<Integer> values) {
            addCriterion("dataAuthorityID in", values, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidNotIn(List<Integer> values) {
            addCriterion("dataAuthorityID not in", values, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidBetween(Integer value1, Integer value2) {
            addCriterion("dataAuthorityID between", value1, value2, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andDataauthorityidNotBetween(Integer value1, Integer value2) {
            addCriterion("dataAuthorityID not between", value1, value2, "dataauthorityid");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerIsNull() {
            addCriterion("isSpecialPower is null");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerIsNotNull() {
            addCriterion("isSpecialPower is not null");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerEqualTo(Byte value) {
            addCriterion("isSpecialPower =", value, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerNotEqualTo(Byte value) {
            addCriterion("isSpecialPower <>", value, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerGreaterThan(Byte value) {
            addCriterion("isSpecialPower >", value, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerGreaterThanOrEqualTo(Byte value) {
            addCriterion("isSpecialPower >=", value, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerLessThan(Byte value) {
            addCriterion("isSpecialPower <", value, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerLessThanOrEqualTo(Byte value) {
            addCriterion("isSpecialPower <=", value, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerIn(List<Byte> values) {
            addCriterion("isSpecialPower in", values, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerNotIn(List<Byte> values) {
            addCriterion("isSpecialPower not in", values, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerBetween(Byte value1, Byte value2) {
            addCriterion("isSpecialPower between", value1, value2, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andIsspecialpowerNotBetween(Byte value1, Byte value2) {
            addCriterion("isSpecialPower not between", value1, value2, "isspecialpower");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeIsNull() {
            addCriterion("superiorUnitCode is null");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeIsNotNull() {
            addCriterion("superiorUnitCode is not null");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeEqualTo(Integer value) {
            addCriterion("superiorUnitCode =", value, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeNotEqualTo(Integer value) {
            addCriterion("superiorUnitCode <>", value, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeGreaterThan(Integer value) {
            addCriterion("superiorUnitCode >", value, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("superiorUnitCode >=", value, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeLessThan(Integer value) {
            addCriterion("superiorUnitCode <", value, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeLessThanOrEqualTo(Integer value) {
            addCriterion("superiorUnitCode <=", value, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeIn(List<Integer> values) {
            addCriterion("superiorUnitCode in", values, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeNotIn(List<Integer> values) {
            addCriterion("superiorUnitCode not in", values, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeBetween(Integer value1, Integer value2) {
            addCriterion("superiorUnitCode between", value1, value2, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andSuperiorunitcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("superiorUnitCode not between", value1, value2, "superiorunitcode");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
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