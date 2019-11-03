package cn.com.gome.page.field.validator;

import com.google.common.base.Strings;

/**
 * Created by yangyu-ds on 2018/3/9.
 */
public class RequiredFieldValidator  implements FieldValidator{


    @Override
    public void validate(String fieldName, String parameterValue) {
        if(Strings.isNullOrEmpty(parameterValue)){
            throw new FieldValidationException("输入" + fieldName + "的值不能为空");
        }
    }
}
