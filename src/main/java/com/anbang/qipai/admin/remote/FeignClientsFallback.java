package com.anbang.qipai.admin.remote;

import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

@Service
public class FeignClientsFallback implements MemberQipaClients, NoticeQipaClients, MailQipaClients {

	@Override
	public CommonRemoteVO qipaAdmin(Integer goldForNewMember) {
		return null;
	}

	@Override
	public CommonRemoteVO addNotice(String notice) {
		return null;
	}

	@Override
	public CommonRemoteVO addmail(String mail) {
		return null;
	}

}
