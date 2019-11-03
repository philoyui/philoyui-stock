package cn.com.gome.page.field.linkin;

import cn.com.gome.page.field.domain.PageDomainProvider;

import java.util.List;

public interface LinkInProvider extends PageDomainProvider {

    /**
     * 获取联动JSON
     * @return
     */
    String obtainSelectJson();

    /**
     * 获取联动信息配置
     * @return
     */
    List<LinkInDomain> buildLinkInConfigs();

}
