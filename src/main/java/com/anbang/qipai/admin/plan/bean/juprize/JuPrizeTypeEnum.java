package com.anbang.qipai.admin.plan.bean.juprize;

import java.util.LinkedHashMap;

public enum JuPrizeTypeEnum {
    hongbaodian;

    public static LinkedHashMap getJuPrizeType(){
        LinkedHashMap map = new LinkedHashMap();
        map.put(hongbaodian.name(), "红包点");
        return map;
    }
}
