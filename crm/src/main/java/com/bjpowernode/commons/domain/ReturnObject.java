package com.bjpowernode.commons.domain;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;

/**
 * ClassName:ReturnObject
 * Package:com.bjpowernode.commons
 * Description: 描述信息
 *
 * @date:2022/3/13 0:12
 * @author:白白白
 */
@Data
public class ReturnObject {
    private String code;
    private String message;
    private Object otherData;
}
