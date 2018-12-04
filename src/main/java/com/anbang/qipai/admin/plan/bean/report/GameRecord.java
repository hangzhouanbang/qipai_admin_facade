package com.anbang.qipai.admin.plan.bean.report;

import lombok.Data;

/**
 * @author YaphetS
 * @date 2018/11/30
 */
@Data
public class GameRecord {
    private String id;

    /**
     * 最后一次记录时间
     */
    private Long createTime;

    /**
     * 用户id
     */
    private String memberId;

    /**
     * 日均在线时长
     */
    private Long DayOnlineTime;

    public GameRecord() {
    }

    public GameRecord(Long createTime, String memberId, Long dayOnlineTime) {
        this.createTime = createTime;
        this.memberId = memberId;
        DayOnlineTime = dayOnlineTime;
    }
}
