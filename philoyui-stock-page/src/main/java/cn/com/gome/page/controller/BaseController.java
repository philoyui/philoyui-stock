package cn.com.gome.page.controller;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.excp.GmosException;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public abstract class BaseController {

    protected String checkCanOperate(HttpServletRequest request, PageService pageService, String loginUsername) {
        PageConfig pageConfig = pageService.getPageConfig();

        String id = request.getParameter("id");

        Serializable entity = null;

        String loginUserFieldName = pageConfig.getLoginUserFieldName();
        if(!Strings.isNullOrEmpty(loginUserFieldName)){
            SearchFilter searchFilter = SearchFilter.getDefault();
            searchFilter.add(Restrictions.eq(loginUserFieldName,loginUsername));
            searchFilter.add(Restrictions.eq("id",id));
            entity = pageService.get(searchFilter);
        }else{
            entity = pageService.get(id);
        }
        if(entity==null){
            throw new GmosException("请求对象不存在");
        }
        return id;
    }

}
