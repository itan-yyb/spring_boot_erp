package com.itan.sys.common;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息提示类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
    public static final ResultObj LOGIN_SUCCESS = new ResultObj(Contast.OK,"登录成功");
    public static final ResultObj LOGIN_ERROR_PASS = new ResultObj(Contast.ERROR,"登录失败，用户名或密码不正确");
    public static final ResultObj LOGIN_ERROR_CODE = new ResultObj(Contast.ERROR,"验证码不正确");

    public static final ResultObj ADD_SUCCESS = new ResultObj(Contast.OK,"添加成功");
    public static final ResultObj ADD_ERROR = new ResultObj(Contast.ERROR,"添加失败");

    public static final ResultObj UPDATE_SUCCESS = new ResultObj(Contast.OK,"修改成功");
    public static final ResultObj UPDATE_ERROR = new ResultObj(Contast.ERROR,"修改失败");

    public static final ResultObj DELETE_SUCCESS = new ResultObj(Contast.OK,"删除成功");
    public static final ResultObj DELETE_ERROR = new ResultObj(Contast.ERROR,"删除失败");

    public static final ResultObj RESET_SUCCESS = new ResultObj(Contast.OK,"重置成功");
    public static final ResultObj RESET_ERROR = new ResultObj(Contast.ERROR,"重置失败");

    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(Contast.OK,"授权成功");
    public static final ResultObj DISPATCH_ERROR = new ResultObj(Contast.ERROR,"授权失败");

    public static final ResultObj OPERATE_SUCCESS = new ResultObj(Contast.OK,"退货成功");
    public static final ResultObj OPERATE_ERROR = new ResultObj(Contast.ERROR,"退货失败");

    private Integer code;
    private String msg;
}
