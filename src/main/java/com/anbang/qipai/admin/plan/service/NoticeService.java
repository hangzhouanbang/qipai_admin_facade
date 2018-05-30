package com.anbang.qipai.admin.plan.service;

import java.util.Map;

import com.anbang.qipai.admin.plan.domain.Notice;

public interface NoticeService {
	/**添加系统公告
	 * @param notice  系统公告内容
	 * **/
	Notice addNotice(Notice notice);
	
	Map<String,Object> findAll(Integer page,Integer size);
	
	void updateNotice(Notice notice);
	
	Notice queryByState(Integer state);
}
