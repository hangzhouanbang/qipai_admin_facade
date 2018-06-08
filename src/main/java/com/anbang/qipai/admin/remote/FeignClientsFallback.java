package com.anbang.qipai.admin.remote;

import org.springframework.stereotype.Service;


@Service
public class FeignClientsFallback implements MemberQipaClients,NoticeQipaClients,MailQipaClients{

	@Override
	public CommonVO qipaAdmin(Integer goldForNewMember) {
		return null;
	}

	@Override
	public CommonVO addNotice(String notice) {
		return null;
	}

	@Override
	public CommonVO addmail(String mail) {
		return null;
	}

}
