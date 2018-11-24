package com.anbang.qipai.admin.web.vo.reportvo;

/**
 * 统计实时更新的数据
 * @author YaphetS
 * @date 2018/11/22
 */
public class CurrentCountVO {
    /**
     * 今日新增
     */
    private Integer addCountToday;
    /**
     * 在线人数
     */
    private Integer onlineCount;
    /**
     * 启动次数
     */
    private Integer launchCount;
    /**
     * 活跃用户
     */
    private Integer activeUserCount;

    public CurrentCountVO() {
    }

    public CurrentCountVO(Integer addCountToday, Integer onlineCount, Integer launchCount, Integer activeUserCount) {
        this.addCountToday = addCountToday;
        this.onlineCount = onlineCount;
        this.launchCount = launchCount;
        this.activeUserCount = activeUserCount;
    }

    public Integer getAddCountToday() {
        return addCountToday;
    }

    public void setAddCountToday(Integer addCountToday) {
        this.addCountToday = addCountToday;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    public Integer getLaunchCount() {
        return launchCount;
    }

    public void setLaunchCount(Integer launchCount) {
        this.launchCount = launchCount;
    }

    public Integer getActiveUserCount() {
        return activeUserCount;
    }

    public void setActiveUserCount(Integer activeUserCount) {
        this.activeUserCount = activeUserCount;
    }

    @Override
    public String toString() {
        return "CurrentCountVO{" +
                "addCountToday=" + addCountToday +
                ", onlineCount=" + onlineCount +
                ", launchCount=" + launchCount +
                ", activeUserCount=" + activeUserCount +
                '}';
    }
}
