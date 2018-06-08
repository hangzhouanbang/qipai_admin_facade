package com.anbang.qipai.admin.plan.service;

import java.util.Map;

import com.anbang.qipai.admin.plan.domain.notice.Notice;
/**系统公告service
 * @author 程佳 2018.5.31
 * **/
public interface NoticeService {
	/**添加系统公告
	 * @param notice  添加系统公告信息
	 * @return 返回对象
	 * **/
	Notice addNotice(Notice notice);
	
	/**查询历史系统公告
	 * @param page 当前页，size 每页条数
	 * @return 返回查询结果
	 * **/
	Map<String,Object> findAll(Integer page,Integer size);
	
	/**修改公告状态
	 * @param notice 修改的公告信息
	 * **/
	void updateNotice(Notice notice);
	
	/**查询公告状态
	 * @param state 公告状态
	 * **/
	Notice queryByState(Integer state);
}
