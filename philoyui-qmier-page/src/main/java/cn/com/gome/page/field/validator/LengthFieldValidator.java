package cn.com.gome.page.field.validator;

/**
 *
 * 字段属性长度校验
 *
 * Created by yangyu-ds on 2018/3/9.
 */
public class LengthFieldValidator  implements FieldValidator{

    private int minLength;
    private int maxLength;

    public LengthFieldValidator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void validate(String fieldName, String parameterValue) {
        if(parameterValue!=null&&parameterValue.length()<minLength){
            throw new FieldValidationException("输入的" + fieldName + "少于最小长度");
        }

        if(parameterValue!=null&&parameterValue.length()>maxLength){
            throw new FieldValidationException("输入的" + fieldName + "超出限制长度");
        }
    }
}
