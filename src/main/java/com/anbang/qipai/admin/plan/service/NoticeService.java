package com.anbang.qipai.admin.plan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.notice.Notice;
import com.anbang.qipai.admin.plan.dao.noticedao.NoticeDao;

@Service
public class NoticeService{
	
	@Autowired
	private NoticeDao noticeDao;
	
	public Notice addNotice(Notice notice) {
		return noticeDao.addNotice(notice);
	}
	
	public Notice queryByState(Integer state) {
		return noticeDao.queryByState(state);
	}

	public void updateNotice(Notice notice) {
	    noticeDao.updateNotice(notice);
	}

	public Map<String,Object> findAll(Integer page, Integer size) {
		return noticeDao.findAll(page, size);
	}

}
