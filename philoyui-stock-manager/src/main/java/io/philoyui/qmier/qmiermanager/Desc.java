package io.philoyui.qmier.qmiermanager;

public @interface Desc {

    /**
     * 描述名字
     * @return
     */
    String name();

    /**
     * 支持搜索
     * @return
     */
    boolean filter() default true;

    /**
     * 参数是否必须填写
     * @return
     */
    boolean require()default false;

    /**
     * 是否支持排序查询
     * @return
     */
    boolean order() default false;

    /**
     * 是否可以编辑
     * @return
     */
    boolean writable() default true;

    /**
     * 指定类型，比如image,editor,textarea,enable
     * @return
     */
    String type() default "";
}
