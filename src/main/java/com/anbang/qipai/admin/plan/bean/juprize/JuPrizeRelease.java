package com.anbang.qipai.admin.plan.bean.juprize;

import com.anbang.qipai.admin.plan.bean.games.Game;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @Description: 对局奖励发布
 */
public class JuPrizeRelease {
    @Id
    private Game game;
    private boolean release;
    private long creatTime;

    private List<JuPrize> snapshot;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public List<JuPrize> getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(List<JuPrize> snapshot) {
        this.snapshot = snapshot;
    }
}
