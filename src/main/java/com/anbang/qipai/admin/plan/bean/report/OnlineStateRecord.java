package com.anbang.qipai.admin.plan.bean.report;

import lombok.Data;

/**
 * @author YaphetS
 * @date 2018/11/24
 */
@Data
public class OnlineStateRecord {
    public static final int ON_LINE=0;
    public static final int OFF_LINE=1;
    /**
     * 				_id,	memberid,	creattime,	onlinestate（0，1),
     */
    private String id;
    /**
     * 用户id
     */
    private String memberId;
    /**
     * 记录时间
     */
    private long createTime;
    /**
     * 上下线状态：0（上线）,1(下线)
     */
    private Integer onlineState;

    public OnlineStateRecord() {
    }

    public OnlineStateRecord(String memberId, long createTime, Integer onlineState) {
        this.memberId = memberId;
        this.createTime = createTime;
        this.onlineState = onlineState;
    }
}
