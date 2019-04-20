package com.anbang.qipai.admin.plan.service.chaguanservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanYushiRecordDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanYushiRecordDboDao;

@Service
public class ChaguanYushiService {

	@Autowired
	private ChaguanYushiRecordDboDao chaguanYushiRecordDboDao;

	public void recordChaguanYushiRecordDbo(ChaguanYushiRecordDbo dbo) {
		chaguanYushiRecordDboDao.save(dbo);
	}
}
