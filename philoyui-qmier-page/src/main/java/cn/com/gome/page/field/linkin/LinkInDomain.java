package cn.com.gome.page.field.linkin;

public class LinkInDomain {

    /**
     * 参与联动字段ID
     */
    private String fieldId;

    /**
     * 参与联动中文名
     */
    private String domainChineseName;


    /**
     * 字段的值
     */
    private String fieldValue;


    public LinkInDomain(String fieldId, String domainChineseName) {
        this.fieldId = fieldId;
        this.domainChineseName = domainChineseName;
    }

    public LinkInDomain(String fieldId, String domainChineseName,String fieldValue) {
        this.fieldId = fieldId;
        this.domainChineseName = domainChineseName;
        this.fieldValue = fieldValue;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getDomainChineseName() {
        return domainChineseName;
    }

    public void setDomainChineseName(String domainChineseName) {
        this.domainChineseName = domainChineseName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
