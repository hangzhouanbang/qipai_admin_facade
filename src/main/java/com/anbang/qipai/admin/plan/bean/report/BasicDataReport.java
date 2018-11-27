package com.anbang.qipai.admin.plan.bean.report;

import lombok.Data;

/**
 * 每小时在线人数表
 * @author YaphetS
 * @date 2018/11/23
 */
@Data
public class BasicDataReport {

    private String id;

    /**
     * 时间(20180101 0点)
     */
    private Long createTime;

    /**
     * 当前在线(90)
     */
    private Integer currentQuantity;

    /**
     * 最高在线(100)
     */
    private Integer maxQuantity;

    public BasicDataReport() {
    }

    public BasicDataReport(Long createTime, Integer currentQuantity, Integer maxQuantity) {
        this.createTime = createTime;
        this.currentQuantity = currentQuantity;
        this.maxQuantity = maxQuantity;
    }
}
