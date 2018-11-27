package com.anbang.qipai.admin.web.vo.reportvo;

import lombok.Data;

/**
 * 统计实时更新的数据
 * @author YaphetS
 * @date 2018/11/22
 */
@Data
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
    private Long launchCount;
    /**
     * 活跃用户
     */
    private Integer activeUserCount;

    public CurrentCountVO() {
    }

    public CurrentCountVO(Integer addCountToday, Integer onlineCount, Long launchCount, Integer activeUserCount) {
        this.addCountToday = addCountToday;
        this.onlineCount = onlineCount;
        this.launchCount = launchCount;
        this.activeUserCount = activeUserCount;
    }

}
