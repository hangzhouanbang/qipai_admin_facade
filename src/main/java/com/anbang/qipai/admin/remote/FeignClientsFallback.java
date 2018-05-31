package com.anbang.qipai.admin.remote;

import org.springframework.stereotype.Service;



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
