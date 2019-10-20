package cn.com.gome.page.field.validator;


import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * 校验邮箱的校验器
 *
 * Created by yangyu-ds on 2018/3/9.
 */
public class EmailFieldValidator  implements FieldValidator{
    @Override
    public void validate(String fieldName, String parameterValue) {
        boolean valid = EmailValidator.getInstance().isValid(parameterValue);
        if(!valid){
            throw new FieldValidationException("输入"+fieldName+"的字段值需为邮箱格式");
        }
    }
}
