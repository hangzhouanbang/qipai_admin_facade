package com.anbang.qipai.admin.plan.bean.report;

/**
 * @author YaphetS
 * @date 2018/11/19
 */
public class AddUserCount {
    private long date;
    private int newMember;
    private int totalMember;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getNewMember() {
        return newMember;
    }

    public void setNewMember(int newMember) {
        this.newMember = newMember;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(int totalMember) {
        this.totalMember = totalMember;
    }
}
