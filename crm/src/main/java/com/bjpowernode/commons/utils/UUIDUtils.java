package com.bjpowernode.commons.utils;

import java.util.UUID;

public class UUIDUtils {
    // 返回32位随机字符串
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
