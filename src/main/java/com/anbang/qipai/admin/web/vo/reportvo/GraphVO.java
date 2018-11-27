package com.anbang.qipai.admin.web.vo.reportvo;

import lombok.Data;

import java.util.List;

/**
 * 折线图
 * @author YaphetS
 * @date 2018/11/22
 */
@Data
public class GraphVO {
    int[] countByToday;
    int[] countByWeek;
    int[] countByMonth;

    public GraphVO() {
    }

    public GraphVO(int[] countByToday, int[] countByWeek, int[] countByMonth) {
        this.countByToday = countByToday;
        this.countByWeek = countByWeek;
        this.countByMonth = countByMonth;
    }
}
