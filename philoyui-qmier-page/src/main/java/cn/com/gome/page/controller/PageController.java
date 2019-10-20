package cn.com.gome.page.controller;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.TableAction;
import cn.com.gome.page.column.TableColumn;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageManager;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.FilterField;
import cn.com.gome.page.field.SortDefinition;
import cn.com.gome.page.utils.BeanUtils;
import cn.com.gome.page.utils.PageConstant;
import cn.com.gome.page.utils.PageWapper;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class PageController {

    @Autowired
    private PageManager pageManager;


    /**
     *
     *
     * @param request
     * @param domainName
     * @param model
     * @return
     */
    @RequestMapping("/{domainName}/page")
    public String page(HttpServletRequest request, @PathVariable String domainName, Model model){

        PageService pageService = pageManager.findServiceByDomainName(domainName);

        String pageNo = extractRequestPageNo(request);
        String pageSize = extractRequestPageSize(request);

        PageConfig pageConfig = pageService.getPageConfig();

        SearchFilter searchFilter = SearchFilter.getPagedSearchFilter(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        configQueryToSearchFilter(pageService,request, searchFilter);
        configOrderToSearchFilter(pageService,request, searchFilter);

        PageObject pageObject = pageService.paged(searchFilter);

        model.addAttribute("page",new PageWapper(pageObject,request.getRequestURI()));
        model.addAttribute("domainChineseName", pageConfig.getDomainChineseName());
        model.addAttribute("domainName", pageConfig.getDomainName());
        model.addAttribute("filterHtml",buildFilterHtml(pageService,request));
        model.addAttribute("existFilter",pageConfig.existFilter());
        model.addAttribute("batchButtonHtml", buildTableActionHtml(request,pageConfig));
        model.addAttribute("tableHeaderHtml",buildTableHeaderHtml(pageConfig));
        model.addAttribute("tableHtml", buildTableValueHtml(request,pageObject,pageConfig));
        model.addAttribute("linkInFieldIds", StringUtils.join(pageConfig.getLinkInFieldIds(),","));
        model.addAttribute("linkInDomains", StringUtils.join(pageConfig.getLinkInFieldDomains(),","));
        model.addAttribute("queryString", Strings.isNullOrEmpty(request.getQueryString())?"":"?" + request.getQueryString());

        return "admin/list";

    }

    /**
     * 构建表格内容
     *
     * @param request
     * @param pageObject
     * @return
     */
    private String buildTableValueHtml(HttpServletRequest request, PageObject pageObject, PageConfig pageConfig) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object entity : pageObject.getContent()) {
            Object id = BeanUtils.getPropertyValue(entity,"id");
            stringBuilder.append("<tr class=\"text-c\" aid=\"").append(id).append("\">");
            for (TableColumn tableColumn : pageConfig.getTableColumns()) {
                stringBuilder.append("<td>").append(tableColumn.generateTableValueHtml(request,pageConfig,tableColumn,entity)).append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        return stringBuilder.toString();
    }

    /**
     * 构建表格头部
     * @return
     */
    private String buildTableHeaderHtml(PageConfig pageConfig) {

        StringBuilder stringBuilder = new StringBuilder();

        for (TableColumn tableColumn : pageConfig.getTableColumns()) {
            stringBuilder.append("<th width=\"")
                    .append(tableColumn.getWidth())
                    .append("%")
                    .append("\">")
                    .append(tableColumn.getColumnName())
                    .append("</th>");
        }

        return stringBuilder.toString();

    }



    /**
     *
     * 构建批量操作的HTML和javascript
     *
     * @param request
     * @return
     */
    private String buildTableActionHtml(HttpServletRequest request, PageConfig pageConfig) {

        StringBuilder stringBuilder = new StringBuilder();

        TableAction[] tableActions = pageConfig.getTableActions();

        for (TableAction tableAction : tableActions) {
            stringBuilder.append(tableAction.generateBatchButtonHtml(pageConfig,request));
        }

        return stringBuilder.toString();
    }

    /**
     *
     * 构造筛选查询条件代码
     *
     * @param request
     * @return
     */
    private String buildFilterHtml(PageService pageService, HttpServletRequest request) {

        PageConfig pageConfig = pageService.getPageConfig();

        StringBuilder stringBuilder = new StringBuilder();
        for (FilterField filterField : pageConfig.getFilterFields()) {
            stringBuilder.append(filterField.buildSearchFieldHtml(pageService, request));
        }

        SortDefinition sortDefinition = pageConfig.getSortDefinition();

        if(sortDefinition!=null){
            stringBuilder.append(sortDefinition.buildOrderFilterHtml(request));
        }
        return stringBuilder.toString();
    }

    /**
     *
     * 提取请求中的pageNo
     *
     * @param request
     * @return
     */
    private String extractRequestPageNo(HttpServletRequest request) {
        String pageNo = request.getParameter("pagenumber");
        if(Strings.isNullOrEmpty(pageNo)){
            pageNo = "0";
        }
        return pageNo;
    }

    /**
     * 提取请求中的pageSize
     * @param request
     * @return
     */
    private String extractRequestPageSize(HttpServletRequest request) {
        String pageSize = request.getParameter("pagesize");
        if(Strings.isNullOrEmpty(pageSize)){
            pageSize = "20";
        }
        return pageSize;
    }

    /**
     *
     * 从request中获取orderField字段的值，如：id_desc;createdTime_desc
     *
     * searchFilter.add(Order.desc(fieldName))
     *
     * 遍历所有的可排序的字段，从request中取得排序查询，构建SearchFilter
     *
     * @param pageService
     * @param request
     * @param searchFilter
     */
    private void configOrderToSearchFilter(PageService pageService, HttpServletRequest request, SearchFilter searchFilter) {
        PageConfig pageConfig = pageService.getPageConfig();
        SortDefinition sortDefinition = pageConfig.getSortDefinition();
        String orderParameterValue = request.getParameter(PageConstant.REQUEST_ORDER_FIELD);
        if (!Strings.isNullOrEmpty(orderParameterValue)) {
            sortDefinition.buildSearchFilterOrder(searchFilter,orderParameterValue);
        }
    }

    /**
     * 遍历可查询的所有字段，分别从request中取得查询值，构建SearchFilter
     * @param pageService
     * @param request
     * @param searchFilter
     */
    private void configQueryToSearchFilter(PageService pageService, HttpServletRequest request, SearchFilter searchFilter) {

        PageConfig pageConfig = pageService.getPageConfig();

        for (FilterField filterField : pageConfig.getFilterFields()) {
            filterField.constructSearchFilter(request, searchFilter);
        }

        String ownerFieldName = pageConfig.getLoginUserFieldName();
        if(!Strings.isNullOrEmpty(ownerFieldName)){
            searchFilter.add(Restrictions.eq(ownerFieldName,pageManager.getLoginUsername()));
        }

    }

}
