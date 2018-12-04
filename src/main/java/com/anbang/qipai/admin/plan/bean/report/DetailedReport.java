package com.anbang.qipai.admin.plan.bean.report;

import lombok.Data;

/**
 * 明细表(按天划分,所有数据汇聚到一张表中)
 * @author YaphetS
 * @date 2018/11/26
 */
@Data
public class DetailedReport {
    private String id;

    /**
     * 创建时间(以小时为精度的时间戳)
     */
    private Long createTime;
    /**
     * 同时在线
     */
    private Integer onlineCount;
    /**
     * 峰值时段
     */
    private Long maxOnlineTime;
    /**
     * 登陆用户数
     */
    private Integer loginUser;
    /**
     * 启动次数统计
     */
    private Integer powerCount;

    public DetailedReport() {
    }

    public DetailedReport(Long createTime, Integer onlineCount, Long maxOnlineTime, Integer loginUser) {
        this.createTime = createTime;
        this.onlineCount = onlineCount;
        this.maxOnlineTime = maxOnlineTime;
        this.loginUser = loginUser;
    }

    public DetailedReport(Long createTime, Integer onlineCount, Long maxOnlineTime) {
        this.createTime = createTime;
        this.onlineCount = onlineCount;
        this.maxOnlineTime = maxOnlineTime;
    }

    public DetailedReport(Long createTime,Integer loginUser) {
        this.createTime = createTime;
        this.loginUser = loginUser;
    }

}
