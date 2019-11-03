package cn.com.gome.cloud.openplatform.common;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yangyu-ds on 2016/9/18.
 */
public class SimpleExpression implements Serializable{

    /**
     * @Fields : 属性名
     */
    private String fieldName;
    /**
     * @Fields : 对应值
     */
    private Object value;
    /**
     * @Fields : 计算符
     */
    private Operator operator;

    public SimpleExpression() {
    }

    public SimpleExpression(String fieldName, Serializable value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Operator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path expression;
        if (fieldName.contains(".")) {
            String[] names = fieldName.split("\\.");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            expression = root.get(fieldName);
        }

        if(expression.getJavaType()==Date.class&&value.getClass().isAssignableFrom(Long.class)){
            Long longDateValue = (Long)value;
            value = new Date(longDateValue);
        }

        return operator.constructBuilder(builder,expression, value);
    }

}
