package cn.com.gome.cloud.openplatform.generator.anno;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})//次注解作用于类和字段上
public @interface DescEntity {

    /**
     * 名字
     * @return
     */
    String name();

    /**
     * DomainName
     * @return
     */
    String domainName();
}
