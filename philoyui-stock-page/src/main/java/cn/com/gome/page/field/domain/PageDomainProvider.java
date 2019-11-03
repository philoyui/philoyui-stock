package cn.com.gome.page.field.domain;

import java.util.List;

public interface PageDomainProvider<T> {


    /**
     * 根据ReferId获取对象
     * @param referId
     * @return
     */
    Object findByReferId(String referId);

    /**
     * 领域对象
     * @return
     */
    String getDomainName();

    /**
     * 中文名称
     * @return
     */
    String getDomainChineseName();

    /**
     * 描述字段pageDomainProvider
     * @return
     */
    String getDisplayFieldName();

    List<T> findAll();
}
