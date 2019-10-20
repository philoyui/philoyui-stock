package cn.com.gome.cloud.openplatform.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yangyu-ds on 2016/9/18.
 */
public class Restrictions implements Serializable {

    private Restrictions() {
    }

    /**
     * @Title : and
     * @Description : 并且
     */
    public static LogicalExpression and(SimpleExpression... simpleExpressions) {
        return new LogicalExpression(simpleExpressions, Operator.AND);
    }

    /**
     * @Title : eq
     * @Description : 等于
     */
    public static SimpleExpression eq(String fieldName, Serializable value) {
        return eq(fieldName, value, true);
    }

    /**
     * @Title : eq
     * @Description : 等于
     */
    public static SimpleExpression eq(String fieldName, Serializable value, boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Operator.EQ);
    }

    /**
     * @Title : gt
     * @Description : 大于
     */
    public static SimpleExpression gt(String fieldName, Serializable value) {
        return gt(fieldName, value, true);
    }

    /**
     * @Title : gt
     * @Description : 大于
     */
    public static SimpleExpression gt(String fieldName, Serializable value, boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Operator.GT);
    }

    /**
     * @Title : gte
     * @Description : 小于等于
     */
    public static SimpleExpression gte(String fieldName, Serializable value) {
        return gte(fieldName, value, true);
    }

    /**
     * @Title : gte
     * @Description : 大于等于
     */
    public static SimpleExpression gte(String fieldName, Serializable value, boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Operator.GTE);
    }

    /**
     * @Title : in
     * @Description : 包含于
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection<Serializable> value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        List<SimpleExpression> expressions = new ArrayList<>();
        for (Serializable obj : value) {
            expressions.add(new SimpleExpression(fieldName, obj, Operator.EQ));
        }
        return new LogicalExpression(expressions.toArray(new SimpleExpression[expressions.size()]), Operator.OR);
    }

    /**
     * @Title : like
     * @Description : 模糊匹配
     */
    public static SimpleExpression like(String fieldName, String value) {
        return like(fieldName, value, true);
    }

    /**
     * @Title : like
     * @Description : 模糊匹配
     */
    public static SimpleExpression like(String fieldName, String value, boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Operator.LIKE);
    }

    /**
     * @Title : lt
     * @Description : 小于
     */
    public static SimpleExpression lt(String fieldName, Serializable value) {
        return lt(fieldName, value, true);
    }

    /**
     * @Title : lt
     * @Description : 小于
     */
    public static SimpleExpression lt(String fieldName, Serializable value, boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Operator.LT);
    }

    /**
     * @Title : lte
     * @Description : 大于等于
     */
    public static SimpleExpression lte(String fieldName, Serializable value) {
        return lte(fieldName, value, true);
    }

    /**
     * @Title : lte
     * @Description : 大于等于
     */
    public static SimpleExpression lte(String fieldName, Serializable value, boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Operator.LTE);
    }

    /**
     * @Title : ne
     * @Description : 不等于
     */
    public static SimpleExpression ne(String fieldName, Serializable value) {
        return ne(fieldName, value, true);
    }

    /**
     * @Title : ne
     * @Description : 不等于
     */
    public static SimpleExpression ne(String fieldName, Serializable value, boolean canNull) {
        if (!canNull && NullUtil.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, Operator.NE);
    }

    /**
     * @Title : or
     * @Description : 或者
     */
    public static LogicalExpression or(SimpleExpression... simpleExpressions) {
        return new LogicalExpression(simpleExpressions, Operator.OR);
    }

    /**
     * @Title :isNull
     * @param fieldName
     * @Description 是否为空
     */
    public static SimpleExpression isNull(String fieldName) {
        return new SimpleExpression(fieldName, null, Operator.ISNULL);
    }
}
