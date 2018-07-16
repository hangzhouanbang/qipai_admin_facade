package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

@FeignClient("qipai-agents")
public interface QipaiAgentsRemoteService {
	@RequestMapping("/apply/applypass")
	public CommonRemoteVO apply_pass(@RequestParam(value = "recordId") String recordId);

	@RequestMapping("/apply/applyrefuse")
	public CommonRemoteVO apply_refuse(@RequestParam(value = "recordId") String recordId);
}
