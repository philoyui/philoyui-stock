package cn.com.gome.page.plugins.style.hui;

import cn.com.gome.page.domain.PublishStatus;
import cn.com.gome.page.plugins.style.StylePlugin;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HuiStylePlugin implements StylePlugin {

    @Override
    public String getListViewFilterTextInputHtml() {
        return "<input type=\"text\" name=\"%s\" id=\"\" placeholder=\"%s\" style=\"width:250px\" class=\"input-text\" value=\"%s\">";
    }

    /**
     * 1. fieldName 2. description
     * @return
     */
    @Override
    public String getListViewOrderFilterHtml(Map<String, String> optionMap, String currentValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<span class=\"select-box inline\"><select name=\"orderField\" class=\"select\">");
        stringBuilder.append("<option value=''> 排序方式 </option>");
        for (Map.Entry<String,String> optionValue : optionMap.entrySet()) {
            stringBuilder.append("<option value=\"").append(optionValue.getKey()).append("\" ").append(appendSelectedClass(currentValue, optionValue.getKey())).append(">")
                    .append(optionValue.getValue()).append("</option>");
        }
        stringBuilder.append("</select></span>");
        return stringBuilder.toString();
    }

    @Override
    public String buildSelectBoxFilterHtml(Map<String, String> nameDescMap, String fieldName, String description, String selectValue) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<span class=\"select-box inline\"><select name=\"").append(fieldName).append("\" class=\"select\">");
        stringBuilder.append("<option value=''>选择 - ").append(description).append(" </option>");
        for (Map.Entry<String,String> optionValue : nameDescMap.entrySet()) {
            stringBuilder.append("<option value=\"").append(optionValue.getKey()).append("\" ").append(appendSelectedClass(selectValue, optionValue.getKey())).append(">")
                    .append(optionValue.getValue()).append("</option>");
        }
        stringBuilder.append("</select></span>");
        return stringBuilder.toString();

    }

    @Override
    public String getFormViewTextInputHtml() {
        return "<input type=\"text\" class=\"input-text\" value=\"%s\" placeholder=\"\" id=\"%s\" name=\"%s\">";
    }

    @Override
    public String buildPublishStatusColumnHtml(PublishStatus publishStatus) {
        switch (publishStatus){
            case PENDING:
                return "<span class=\"label badge-primary radius\">待审核</span>";
            case REFUSE:
                return "<span class=\"label label-danger radius\">未通过</span>";
            case CREATED:
                return "<span class=\"label label-default radius\">已创建</span>";
            case PASS:
                return "<span class=\"label label-success radius\">已发布</span>";
            case DELETE:
                return "<span class=\"label label-danger radius\">已删除</span>";
            default:
                return publishStatus.getDescription();
        }
    }

    @Override
    public String buildEnableHtml(Boolean enable) {

        if(enable){
            return "<span class=\"label label-success radius\">已启用</span>";
        }else{
            return "<span class=\"label radius\">已停用</span>";
        }

    }


    @Override
    public String getBatchActionHtml() {
        return "<a href=\"javascript:;\" onclick=\"batch_action(\'#名字#','#操作链接#')\" style=\"margin-left:5px\" class=\"#Class样式#\"><i class=\"Hui-iconfont\">\uE6E2</i>#名字#</a>";
    }

    @Override
    public String getConfirmTableActionHtml() {
        return "<a href=\"javascript:;\" onclick=\"confirm_link(\'确认#名字#吗？','put','#操作链接#')\" style=\"margin-left:5px\" class=\"#Class样式#\"><i class=\"Hui-iconfont\">\uE6E2</i>#名字#</a>";
    }

    @Override
    public String getBatchDeleteActionHtml() {
        return "<a href=\"javascript:;\" onclick=\"batch_del()\" style=\"margin-left:5px\" class=\"btn btn-danger radius\"><i class=\"Hui-iconfont\">\uE6E2</i> 批量删除</a>";
    }

    @Override
    public String getCreateActionHtml(String action) {
        return "<a class=\"btn btn-primary radius\" style=\"margin-left:5px\" onclick=\"add('添加#domain中文名字#','#domainName#','" + action + "')\" href=\"javascript:;\"><i class=\"Hui-iconfont\">\uE600</i> 添加#domain中文名字#</a>";
    }

    private static String appendSelectedClass(String selectedValue, String optionValue) {
        return optionValue.equalsIgnoreCase(selectedValue) ? " selected " : "";
    }
}
