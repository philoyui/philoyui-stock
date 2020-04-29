package cn.com.gome.page.plugins.style;

import cn.com.gome.page.domain.PublishStatus;

import java.util.Map;

public interface StylePlugin {


    /**
     * 列表页搜索输入框
     * @return
     */
    String getListViewFilterTextInputHtml();

    String getListViewOrderFilterHtml(Map<String, String> optionMap, String currentValue);

    /**
     * 构造下拉搜索框
     * @param nameDescMap
     * @param fieldName
     * @param description
     * @param selectValue
     * @return
     */
    String buildSelectBoxFilterHtml(Map<String, String> nameDescMap, String fieldName, String description, String selectValue);

    /**
     * 构造表单页输入框
     * @return
     */
    String getFormViewTextInputHtml();

    String buildPublishStatusColumnHtml(PublishStatus publishStatus);

    String buildEnableHtml(Boolean boolValue);

    /**
     * 表格上方通用批量操作按钮，比如创建，批量操作，批量删除
     * @return
     */
    String getBatchActionHtml();

    String getConfirmTableActionHtml();

    /**
     * 批量删除的HTML
     * @return
     */
    String getBatchDeleteActionHtml();

    /**
     * 创建按钮HTML
     * @return
     * @param action
     */
    String getCreateActionHtml(String action);

    /**
     * 日期HTML
     * @return
     */
    String getFormViewDateInputHtml();
}
