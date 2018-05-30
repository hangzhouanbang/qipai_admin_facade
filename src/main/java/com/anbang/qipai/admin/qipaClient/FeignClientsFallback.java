package com.anbang.qipai.admin.qipaClient;

import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.domain.CommonVO;

@Service
public class FeignClientsFallback implements MemberQipaClients,NoticeQipaClients{

	@Override
	public CommonVO qipaAdmin(Integer goldForNewMember) {
		return null;
	}

	@Override
	public CommonVO addNotice(String notice) {
		return null;
	}

}
