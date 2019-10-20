package cn.com.gome.cloud.openplatform.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu-ds on 2016/9/18.
 */
public class LogicalExpression  implements Serializable {
    /**
     * @Fields : 逻辑表达式中包含的表达式
     */
    private SimpleExpression[] simpleExpressions;
    /**
     * @Fields : 计算符
     */
    private Operator operator;

    public LogicalExpression() {
    }

    public LogicalExpression(SimpleExpression[] simpleExpressions, Operator operator) {
        this.simpleExpressions = simpleExpressions;
        this.operator = operator;
    }

    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (int i = 0; i < this.simpleExpressions.length; i++) {
            predicates.add(this.simpleExpressions[i].toPredicate(root, query, builder));
        }
        switch (operator) {
            case AND:
                return builder.and(predicates.toArray(new Predicate[predicates
                        .size()]));
            case OR:
                return builder.or(predicates.toArray(new Predicate[predicates
                        .size()]));
            default:
                return null;
        }
    }

    public SimpleExpression[] getSimpleExpressions() {
        return simpleExpressions;
    }

    public void setSimpleExpressions(SimpleExpression[] simpleExpressions) {
        this.simpleExpressions = simpleExpressions;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
