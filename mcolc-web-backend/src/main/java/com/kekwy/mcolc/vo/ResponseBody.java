package com.kekwy.mcolc.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseBody {
    private int code = 0;
    private String message = "";
    private Object payload = null;

}
