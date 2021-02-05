package com.twkf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description= "返回结果信息")
public class BackMessage<T> {
    @ApiModelProperty(value="状态码",name="state",dataType = "Integer")
    private int state;//返回状态
    @ApiModelProperty(value="状态信息",name="msg",dataType = "String")
    private String msg;//错误信息
    @ApiModelProperty(value="返回数据",name="data",dataType = "Object")
    private T data;//返回数据

}
