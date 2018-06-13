package com.anbang.qipai.admin.plan.dao.mongodb;

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

import com.anbang.qipai.admin.plan.dao.mongodb.repository.NoticeRepository;
import com.anbang.qipai.admin.plan.dao.noticedao.NoticeDao;
import com.anbang.qipai.admin.plan.domain.notice.Notice;
import com.anbang.qipai.admin.plan.service.NoticeService;

@Component
public class MongdbNoticeDao implements NoticeDao{
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Notice addNotice(Notice notice) {
		Integer state = 1;
		Notice notice1 = noticeService.queryByState(state);
			if(notice1 != null) {//有启用状态的公告,修改启用公告的状态
				Notice notice2 = new Notice();
				notice2.setId(notice1.getId());
				notice2.setNotice(notice1.getNotice());
				notice2.setState(0);
				noticeService.updateNotice(notice2);
			}
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
		 Map<String,Object> map = new HashMap<String,Object>();
		 if(page == null || page < 1) {
			 page = 1;
		 }
		 Sort sort = new Sort(Sort.Direction.DESC, "state");
		 
		 Pageable pageable= new PageRequest(page-1, size,sort);
		 
		 Query query = new Query();
		 
		 Long total = mongoTemplate.count(query, Notice.class);//计算总数
		 Long count = total%size==0?total/size:total/size+1;
		 List<Notice> list = mongoTemplate.find(query.with(pageable),Notice.class);
		 map.put("list", list);
		 map.put("count", count);
		return map;
	}

	
}
