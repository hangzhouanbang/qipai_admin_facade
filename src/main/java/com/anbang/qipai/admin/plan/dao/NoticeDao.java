package com.anbang.qipai.admin.plan.dao;

import java.util.Map;

import com.anbang.qipai.admin.plan.domain.Notice;

public interface NoticeDao {
	
	/**添加系统公告
	 * @param notice  系统公告内容
	 * **/
	Notice addNotice(Notice notice);
	
	void updateNotice(Notice notice);
	
	Notice queryByState(Integer state);
	
	Map<String,Object> findAll(Integer page,Integer size);
}
