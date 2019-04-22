package com.anbang.qipai.admin.plan.service.chaguanservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanDbo;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanMemberDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanDboDao;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanMemberDboDao;
import com.highto.framework.web.page.ListPage;

@Service
public class ChaguanService {

	@Autowired
	private ChaguanDboDao chaguanDboDao;

	@Autowired
	private ChaguanMemberDboDao chaguanMemberDboDao;

	public ListPage findChaguanDbo(int page, int size) {
		int amount = (int) chaguanDboDao.count();
		List<ChaguanDbo> chaguanList = chaguanDboDao.find(page, size);
		return new ListPage(chaguanList, page, size, amount);
	}

	public ListPage findChaguanMemberDbo(int page, int size, String chaguanId) {
		int amount = (int) chaguanMemberDboDao.countByChaguanId(chaguanId);
		List<ChaguanMemberDbo> chaguanMemberList = chaguanMemberDboDao.findByChaguanId(page, size, chaguanId);
		return new ListPage(chaguanMemberList, page, size, amount);
	}

	public ChaguanDbo findByChaguanId(String chaguanId) {
		return chaguanDboDao.findByChaguanId(chaguanId);
	}

	public void saveChaguanMemberDbo(ChaguanMemberDbo dbo) {
		chaguanMemberDboDao.save(dbo);
	}

	public void removeChaguanMemberDbo(ChaguanMemberDbo dbo) {
		chaguanMemberDboDao.save(dbo);
	}

	public void setChaguanMemberDbo(ChaguanMemberDbo dbo) {
		chaguanMemberDboDao.save(dbo);
	}

	public void saveChaguanDbo(ChaguanDbo dbo) {
		chaguanDboDao.save(dbo);
	}

	public void updateChaguanDbo(ChaguanDbo dbo) {
		chaguanDboDao.save(dbo);
	}
}
