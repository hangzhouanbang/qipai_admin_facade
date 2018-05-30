package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.NoticeDao;
import com.anbang.qipai.admin.plan.domain.Notice;
import com.anbang.qipai.admin.plan.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public Notice addNotice(Notice notice) {
		return noticeDao.addNotice(notice);
	}

	@Override
	public Notice queryByState(Integer state) {
		return noticeDao.queryByState(state);
	}

	@Override
	public void updateNotice(Notice notice) {
	    noticeDao.updateNotice(notice);
	}

	@Override
	public Map<String,Object> findAll(Integer page, Integer size) {
		return noticeDao.findAll(page, size);
	}

}
