package com.anbang.qipai.admin.plan.dao.daobase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.NoticeDao;
import com.anbang.qipai.admin.plan.dao.daobase.mongodb.NoticeRepository;
import com.anbang.qipai.admin.plan.domain.Notice;

@Component
public class MongdbNoticeDao implements NoticeDao{
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Notice addNotice(Notice notice) {
		return noticeRepository.save(notice);
	}

	@Override
	public Notice queryByState(Integer state) {
		return noticeRepository.queryByState(state);
	}

	@Override
	public void updateNotice(Notice notice) {
		noticeRepository.save(notice);
	}

	@Override
	public Map<String,Object> findAll(Integer page, Integer size) {
		 Map<String,Object> map = new HashMap<>();
		 if(page < 1) {
			 page = 1;
		 }
		 Sort sort = new Sort(Sort.Direction.DESC, "state");
		 
		 Pageable pageable= new PageRequest(page-1, size,sort);
		 
		 Query query = new Query();
		 
		 Long total = mongoTemplate.count(query, Notice.class);//计算总数
		 List<Notice> list = mongoTemplate.find(query.with(pageable),Notice.class);
		 map.put("list", list);
		 map.put("total", total);
		return map;
	}

	
}
