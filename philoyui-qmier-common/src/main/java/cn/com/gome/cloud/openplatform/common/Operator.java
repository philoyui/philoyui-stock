package cn.com.gome.cloud.openplatform.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;

/**
 * Created by yangyu-ds on 2018/3/28.
 */
public enum Operator {
    EQ {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {

            if (expression.getJavaType().isEnum()&&value.getClass().isAssignableFrom(String.class)){
                Enum anEnum = Enum.valueOf(expression.getJavaType(), (String) value);
                return builder.equal(expression, anEnum);
            }

            return builder.equal(expression, value);
        }
    }, NE {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return builder.notEqual(expression, value);
        }
    }, LIKE {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return builder.like(expression, "%" + value + "%");
        }
    }, GT {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return builder.greaterThan(expression, (Comparable) value);
        }
    }, LT {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return builder.lessThan(expression, (Comparable) value);
        }
    }, GTE {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return builder.greaterThanOrEqualTo(expression, (Comparable) value);
        }
    }, LTE {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return builder.lessThanOrEqualTo(expression, (Comparable) value);
        }
    },ISNULL{
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return builder.isNull(expression);
        }
    }, AND {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return null;
        }
    }, OR {
        @Override
        public Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value) {
            return null;
        }
    };

    public abstract Predicate constructBuilder(CriteriaBuilder builder, Path expression, Object value);

}
