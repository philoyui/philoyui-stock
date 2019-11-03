package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.category.CategoryServiceProvider;
import cn.com.gome.page.category.CategoryTree;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.domain.CategoryEntity;
import cn.com.gome.page.form.FormField;
import cn.com.gome.page.plugins.style.StylePlugin;
import cn.com.gome.page.utils.BeanUtils;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryFieldDefinition extends FieldDefinition{

    private CategoryServiceProvider categoryServiceProvider;

    public CategoryFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    public CategoryFieldDefinition(String fieldName, String description, CategoryServiceProvider categoryServiceProvider) {
        super(fieldName, description);
        this.categoryServiceProvider = categoryServiceProvider;
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {
        String fieldValue = request.getParameter(fieldName);
        if (!Strings.isNullOrEmpty(fieldValue)) {
            searchFilter.add(Restrictions.eq(fieldName, categoryServiceProvider.transformToId(fieldValue)));
        }
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        CategoryEntity[] allCategories = categoryServiceProvider.findAllCategoryEntity();
        CategoryTree categoryTree = new CategoryTree(allCategories);
        Map<String, String> optionMap = categoryTree.getOptionHashMap();
        StylePlugin currentStyle = pageContext.getCurrentStyle();
        return currentStyle.buildSelectBoxFilterHtml(optionMap,fieldName,description,request.getParameter(fieldName));
    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object value) {
        String categoryId = String.valueOf(value);
        return buildCategoryNavigation(categoryId);
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {

        Object selectValue = null;

        CategoryEntity[] allCategories = categoryServiceProvider.findAllCategoryEntity();

        CategoryTree categoryTree = new CategoryTree(allCategories);

        Map<String, String> optionMap = categoryTree.getOptionHashMap();

        String parameterValue = request.getParameter(fieldName);


        if (entity != null) {
            selectValue = BeanUtils.getPropertyValue(entity, fieldName);
        }

        if(!Strings.isNullOrEmpty(parameterValue)){
            selectValue = parameterValue;
        }

        return  pageContext.getStylePlugin().buildSelectBoxFilterHtml(optionMap,fieldName,description, selectValue == null ? "" : selectValue.toString());
    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        String categoryId = String.valueOf(entity);
        return buildCategoryNavigation(categoryId);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Category;
    }

    @Override
    public String[] getNeedJsFiles() {
        return new String[0];
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        return categoryServiceProvider.transformToId(parameterValue);
    }

    /**
     * 构造分类的导航树
     * @param categoryId
     * @return
     */
    private String buildCategoryNavigation(String categoryId) {

        if(categoryServiceProvider.isRoot(categoryId)){
            return "根节点";
        }

        StringBuilder stringBuilder = new StringBuilder();
        List<CategoryEntity> categoryEntityList = new ArrayList<>();

        CategoryEntity categoryEntity = categoryServiceProvider.getCategoryEntity(categoryId);
        if(categoryEntity==null){
            return "父分类已不在";
        }

        categoryEntityList.add(categoryEntity);

        while (!categoryEntity.isRootElement()){
            CategoryEntity parentCategoryEntity = categoryServiceProvider.getCategoryEntity(categoryEntity.getParentNodeId());
            if(parentCategoryEntity==null){
                break;
            }
            categoryEntityList.add(parentCategoryEntity);
            categoryEntity = parentCategoryEntity;
        }

        for (int i = categoryEntityList.size() - 1; i >= 0; i--) {
            stringBuilder.append(categoryEntityList.get(i).getNodeName()).append(">");
        }

        if(stringBuilder.length()>0){
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(">"));
        }

        return stringBuilder.toString();
    }
}
