package com.anbang.qipai.admin.plan.service.chaguanservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanApply;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanApplyDao;

@Service
public class ChaguanApplyService {

	@Autowired
	private ChaguanApplyDao chaguanApplyDao;

	public void saveChaguanApply(ChaguanApply apply) {
		chaguanApplyDao.save(apply);
	}

	public void chaguanApplyPass(ChaguanApply apply) {
		chaguanApplyDao.save(apply);
	}

	public void chaguanApplyRefuse(ChaguanApply apply) {
		chaguanApplyDao.save(apply);
	}

}
