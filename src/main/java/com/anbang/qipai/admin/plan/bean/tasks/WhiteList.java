package com.anbang.qipai.admin.plan.bean.tasks;

/**
 * @author yins
 * @Description: 白名单
 */
public class WhiteList {
    private String playerId;
    private long addTime;
    private String remark;
    private String operator;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
