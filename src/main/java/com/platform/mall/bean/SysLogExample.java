package com.platform.mall.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysLogExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNull() {
            addCriterion("action_name is null");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNotNull() {
            addCriterion("action_name is not null");
            return (Criteria) this;
        }

        public Criteria andActionNameEqualTo(String value) {
            addCriterion("action_name =", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotEqualTo(String value) {
            addCriterion("action_name <>", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThan(String value) {
            addCriterion("action_name >", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThanOrEqualTo(String value) {
            addCriterion("action_name >=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThan(String value) {
            addCriterion("action_name <", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThanOrEqualTo(String value) {
            addCriterion("action_name <=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLike(String value) {
            addCriterion("action_name like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotLike(String value) {
            addCriterion("action_name not like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameIn(List<String> values) {
            addCriterion("action_name in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotIn(List<String> values) {
            addCriterion("action_name not in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameBetween(String value1, String value2) {
            addCriterion("action_name between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotBetween(String value1, String value2) {
            addCriterion("action_name not between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionUrlIsNull() {
            addCriterion("action_url is null");
            return (Criteria) this;
        }

        public Criteria andActionUrlIsNotNull() {
            addCriterion("action_url is not null");
            return (Criteria) this;
        }

        public Criteria andActionUrlEqualTo(String value) {
            addCriterion("action_url =", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotEqualTo(String value) {
            addCriterion("action_url <>", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlGreaterThan(String value) {
            addCriterion("action_url >", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlGreaterThanOrEqualTo(String value) {
            addCriterion("action_url >=", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLessThan(String value) {
            addCriterion("action_url <", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLessThanOrEqualTo(String value) {
            addCriterion("action_url <=", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlLike(String value) {
            addCriterion("action_url like", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotLike(String value) {
            addCriterion("action_url not like", value, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlIn(List<String> values) {
            addCriterion("action_url in", values, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotIn(List<String> values) {
            addCriterion("action_url not in", values, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlBetween(String value1, String value2) {
            addCriterion("action_url between", value1, value2, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andActionUrlNotBetween(String value1, String value2) {
            addCriterion("action_url not between", value1, value2, "actionUrl");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingIsNull() {
            addCriterion("message_incoming is null");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingIsNotNull() {
            addCriterion("message_incoming is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingEqualTo(String value) {
            addCriterion("message_incoming =", value, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingNotEqualTo(String value) {
            addCriterion("message_incoming <>", value, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingGreaterThan(String value) {
            addCriterion("message_incoming >", value, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingGreaterThanOrEqualTo(String value) {
            addCriterion("message_incoming >=", value, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingLessThan(String value) {
            addCriterion("message_incoming <", value, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingLessThanOrEqualTo(String value) {
            addCriterion("message_incoming <=", value, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingLike(String value) {
            addCriterion("message_incoming like", value, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingNotLike(String value) {
            addCriterion("message_incoming not like", value, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingIn(List<String> values) {
            addCriterion("message_incoming in", values, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingNotIn(List<String> values) {
            addCriterion("message_incoming not in", values, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingBetween(String value1, String value2) {
            addCriterion("message_incoming between", value1, value2, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageIncomingNotBetween(String value1, String value2) {
            addCriterion("message_incoming not between", value1, value2, "messageIncoming");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedIsNull() {
            addCriterion("message_returned is null");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedIsNotNull() {
            addCriterion("message_returned is not null");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedEqualTo(String value) {
            addCriterion("message_returned =", value, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedNotEqualTo(String value) {
            addCriterion("message_returned <>", value, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedGreaterThan(String value) {
            addCriterion("message_returned >", value, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedGreaterThanOrEqualTo(String value) {
            addCriterion("message_returned >=", value, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedLessThan(String value) {
            addCriterion("message_returned <", value, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedLessThanOrEqualTo(String value) {
            addCriterion("message_returned <=", value, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedLike(String value) {
            addCriterion("message_returned like", value, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedNotLike(String value) {
            addCriterion("message_returned not like", value, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedIn(List<String> values) {
            addCriterion("message_returned in", values, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedNotIn(List<String> values) {
            addCriterion("message_returned not in", values, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedBetween(String value1, String value2) {
            addCriterion("message_returned between", value1, value2, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andMessageReturnedNotBetween(String value1, String value2) {
            addCriterion("message_returned not between", value1, value2, "messageReturned");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andTimespanIsNull() {
            addCriterion("timespan is null");
            return (Criteria) this;
        }

        public Criteria andTimespanIsNotNull() {
            addCriterion("timespan is not null");
            return (Criteria) this;
        }

        public Criteria andTimespanEqualTo(Integer value) {
            addCriterion("timespan =", value, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanNotEqualTo(Integer value) {
            addCriterion("timespan <>", value, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanGreaterThan(Integer value) {
            addCriterion("timespan >", value, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanGreaterThanOrEqualTo(Integer value) {
            addCriterion("timespan >=", value, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanLessThan(Integer value) {
            addCriterion("timespan <", value, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanLessThanOrEqualTo(Integer value) {
            addCriterion("timespan <=", value, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanIn(List<Integer> values) {
            addCriterion("timespan in", values, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanNotIn(List<Integer> values) {
            addCriterion("timespan not in", values, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanBetween(Integer value1, Integer value2) {
            addCriterion("timespan between", value1, value2, "timespan");
            return (Criteria) this;
        }

        public Criteria andTimespanNotBetween(Integer value1, Integer value2) {
            addCriterion("timespan not between", value1, value2, "timespan");
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