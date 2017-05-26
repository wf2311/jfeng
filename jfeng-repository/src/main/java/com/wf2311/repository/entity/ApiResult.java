/**
 *
 */
package com.wf2311.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * api返回基类
 *
 * @param <T>
 * @author wf2311
 * @time 2016-10-14 16:10:14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "api返回基类")
public class ApiResult<T> {


    @ApiModelProperty(name = "返回结果代号", required = true, notes = "主要用于表示返回失败的原因类别")
    private int code = 0;

    @ApiModelProperty(name = "返回结果", required = true, notes = "true:成功;false:失败;")
    private boolean success = true;

    @ApiModelProperty(name = "返回信息")
    private String message;

    @ApiModelProperty(name = "返回结果主体")
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
