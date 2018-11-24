package com.anbang.qipai.admin.web.vo.reportvo;

import java.util.List;

/**
 * 新增用户折线图
 * @author YaphetS
 * @date 2018/11/22
 */
public class AddUserGraphVO {
    int[] addUserToday;
    int[] addUserWeek;
    int[] addUserMonth;

    public AddUserGraphVO() {
    }

    public AddUserGraphVO(int[] addUserToday, int[] addUserWeek, int[] addUserMonth) {
        this.addUserToday = addUserToday;
        this.addUserWeek = addUserWeek;
        this.addUserMonth = addUserMonth;
    }

    public int[] getAddUserToday() {
        return addUserToday;
    }

    public void setAddUserToday(int[] addUserToday) {
        this.addUserToday = addUserToday;
    }

    public int[] getAddUserWeek() {
        return addUserWeek;
    }

    public void setAddUserWeek(int[] addUserWeek) {
        this.addUserWeek = addUserWeek;
    }

    public int[] getAddUserMonth() {
        return addUserMonth;
    }

    public void setAddUserMonth(int[] addUserMonth) {
        this.addUserMonth = addUserMonth;
    }
}
