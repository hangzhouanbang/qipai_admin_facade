package com.anbang.qipai.admin.remote.service;

import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ruianmajiang")
public interface RAMJLogRemoteService {

    @RequestMapping("/snapshot/save")
    CommonRemoteVO snapshot_save();

}
