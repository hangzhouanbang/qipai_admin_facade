package com.anbang.qipai.admin.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

/**
 * 系统公告服务接口
 * 
 * @author 程佳 2018.5.31 声明这是一个Feign客户端，并且指明服务ID
 **/
@FeignClient("qipai-game")
public interface NoticeQipaClients {
	/**
	 * 这里定义了类似于SpringMVC用法的方法，就可以进行RESTful的调用了
	 **/
	@RequestMapping(value = "/notice/addnotice")
	public CommonRemoteVO addNotice(@RequestParam(value = "notice") String notice);

}
