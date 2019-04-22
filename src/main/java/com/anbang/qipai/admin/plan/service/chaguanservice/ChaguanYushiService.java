package com.anbang.qipai.admin.plan.service.chaguanservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanYushiRecordDbo;
import com.anbang.qipai.admin.plan.dao.chaguandao.ChaguanYushiRecordDboDao;
import com.anbang.qipai.admin.web.vo.chaguanvo.ChaguanYushiRecordDboVO;
import com.highto.framework.web.page.ListPage;

@Service
public class ChaguanYushiService {

	@Autowired
	private ChaguanYushiRecordDboDao chaguanYushiRecordDboDao;

	public ListPage findRecordByConditions(int page, int size, ChaguanYushiRecordDboVO record) {
		long amount = chaguanYushiRecordDboDao.countByConditions(record);
		List<ChaguanYushiRecordDbo> recordList = chaguanYushiRecordDboDao.findByConditions(page, size, record);
		ListPage listPage = new ListPage(recordList, page, size, (int) amount);
		return listPage;
	}

	public void recordChaguanYushiRecordDbo(ChaguanYushiRecordDbo dbo) {
		chaguanYushiRecordDboDao.save(dbo);
	}
}
