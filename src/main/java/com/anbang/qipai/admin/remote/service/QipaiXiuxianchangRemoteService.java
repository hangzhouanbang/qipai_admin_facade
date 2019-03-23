package com.anbang.qipai.admin.remote.service;

import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("qipai-xiuxianchang")
public interface QipaiXiuxianchangRemoteService {

    /**
     * 增加休闲场金币
     */
    @RequestMapping("/gold/givegoldtomember")
    CommonRemoteVO giveGoldToMember(@RequestParam(value = "memberId") String memberId,
                                    @RequestParam(value = "amount") int amount,
                                    @RequestParam(value = "textSummary") String textSummary);
}
