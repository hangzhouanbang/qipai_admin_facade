package com.anbang.qipai.admin.plan.service.chaguanservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanDbo;
import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanMemberDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanDboDao;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanMemberDboDao;

@Service
public class ChaguanService {

	@Autowired
	private ChaguanDboDao chaguanDboDao;

	@Autowired
	private ChaguanMemberDboDao chaguanMemberDboDao;

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
