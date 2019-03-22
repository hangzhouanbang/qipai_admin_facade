package com.anbang.qipai.admin.plan.bean.juprize;

import com.anbang.qipai.admin.web.vo.juprize.CommonDropDown;

import java.util.ArrayList;
import java.util.List;

public enum JuPrizeTypeEnum {
    hongbaodian;

    public static List getJuPrizeType(){
        List<CommonDropDown> dropDowns = new ArrayList<>();
        dropDowns.add(new CommonDropDown(hongbaodian.name(), "红包点"));
        return dropDowns;
    }
}
