package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anbang.qipai.admin.plan.domain.Notice;


public interface NoticeRepository extends MongoRepository<Notice, String>{
	/**查询启用状态的公告
	 * @param state 状态
	 * **/
	public Notice queryByState(Integer state);
}
