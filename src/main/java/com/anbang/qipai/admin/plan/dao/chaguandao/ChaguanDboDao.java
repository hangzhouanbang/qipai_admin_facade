package com.anbang.qipai.admin.plan.dao.chaguandao;

import java.util.List;

import com.anbang.qipai.admin.plan.bean.chaguan.ChaguanDbo;

public interface ChaguanDboDao {

	void save(ChaguanDbo dbo);

	long count();

	List<ChaguanDbo> find(int page, int size);

	ChaguanDbo findByChaguanId(String chaguanId);
}
