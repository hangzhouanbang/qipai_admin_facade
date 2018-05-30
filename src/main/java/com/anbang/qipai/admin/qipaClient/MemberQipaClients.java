package com.anbang.qipai.admin.qipaClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.domain.CommonVO;


/**
 * 声明这是一个Feign客户端，并且指明服务ID
 * **/
@FeignClient("qipai-members")
public interface MemberQipaClients {
	
	/**
	 * 这里定义了类似于SpringMVC用法的方法，就可以进行RESTful的调用了
	 * **/
	@RequestMapping(value = "/conf/createmember")
	public CommonVO qipaAdmin(@RequestParam(value = "goldForNewMember") Integer goldForNewMember);
	
}
