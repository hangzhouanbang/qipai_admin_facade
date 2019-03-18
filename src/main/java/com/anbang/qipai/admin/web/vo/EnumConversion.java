package com.anbang.qipai.admin.web.vo;

import com.anbang.qipai.admin.plan.bean.games.Game;

/**
 * @Description:
 */
public class EnumConversion {

    // 游戏名
    public static String getGameName(Game game) {
        switch (game) {
            case ruianMajiang:
                return "瑞安麻将";
            case wenzhouMajiang:
                return "温州麻将";
            case wenzhouShuangkou:
                return "温州双扣";
            case fangpaoMajiang:
                return "放炮麻将";
            case dianpaoMajiang:
                return "点炮麻将";
            case paodekuai:
                return "跑的快";
            case doudizhu:
                return "斗地主";
        }
        return "";
    }
}
