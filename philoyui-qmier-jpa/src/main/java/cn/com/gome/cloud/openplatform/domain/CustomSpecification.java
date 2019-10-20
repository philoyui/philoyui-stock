package cn.com.gome.cloud.openplatform.domain;

import cn.com.gome.cloud.openplatform.common.LogicalExpression;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.common.SimpleExpression;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyu-ds on 2016/9/23.
 */
public class CustomSpecification<T> implements Specification<T> {

    private List<SimpleExpression> simpleExpressions = new ArrayList<>(5);

    private List<LogicalExpression> logicalExpressions = new ArrayList<>(5);

    public CustomSpecification(SearchFilter searchFilter) {
        simpleExpressions = searchFilter.getSimpleExpressions();
        logicalExpressions = searchFilter.getLogicalExpressions();
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (!simpleExpressions.isEmpty()||!logicalExpressions.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for (SimpleExpression simpleExpression : simpleExpressions) {
                predicates.add(simpleExpression.toPredicate(root, criteriaQuery, criteriaBuilder));
            }
            for (LogicalExpression logicalExpression : logicalExpressions) {
                predicates.add(logicalExpression.toPredicate(root, criteriaQuery, criteriaBuilder));
            }
            // 将所有条件用 and 联合起来
            if (!predicates.isEmpty()) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return criteriaBuilder.conjunction();
    }
}
