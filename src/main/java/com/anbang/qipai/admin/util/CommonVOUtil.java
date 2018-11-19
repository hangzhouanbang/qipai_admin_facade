package com.anbang.qipai.admin.util;

import com.anbang.qipai.admin.web.vo.CommonVO;

/**
 * CommonVO的工具类
 * @author YaphetS
 * @date 2018/11/19
 */
public class CommonVOUtil {
    public static CommonVO success(Object data,String msg) {
        CommonVO CommonVO = new CommonVO();
        CommonVO.setData(data);
        CommonVO.setMsg(msg);
        return CommonVO;
    }
}
