package com.itan.sys.common;

public interface Contast {
    /**
     * 状态码
     */
    public static final Integer OK=200;
    public static final Integer ERROR=500;
    /**
     * 用户默认密码
     */
    public static final String USER_DEFAULT_PWD="123456";
    /**
     * 菜单权限
     */
    public static final String TYPE_MENU="menu";
    public static final String TYPE_PERMISSION="permission";
    /**
     * 可用状态
     */
    public static final Object AVAILABLE_TRUE=1;
    public static final Object AVAILABLE_FALSE=0;
    /**
     * 用户类型
     */
    public static final Integer USER_TYPE_SUPER=0;
    public static final Integer USER_TYPE_NORMAL=1;
    /**
     * 展开类型
     */
    public static final Integer OPEN_TRUE=1;
    public static final Integer OPEN_FALSE=0;
    /**
     * 默认图片路径
     */
    public static final String DEFAULT_IMG_PATH="default.jpg";
}
