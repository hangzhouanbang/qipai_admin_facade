package com.anbang.qipai.admin.plan.dao.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anbang.qipai.admin.plan.bean.notice.Notice;

/**连接系统公告表
 * @author 程佳 2018.5.31
 * **/
public interface NoticeRepository extends MongoRepository<Notice, String>{
	/**查询启用状态的公告
	 * @param state 状态
	 * **/
	public Notice queryByState(Integer state);
}
