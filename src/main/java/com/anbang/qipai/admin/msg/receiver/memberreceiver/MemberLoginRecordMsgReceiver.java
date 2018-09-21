package com.anbang.qipai.admin.msg.receiver.memberreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.MemberLoginRecordSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.members.MemberLoginRecord;
import com.anbang.qipai.admin.plan.service.membersservice.MemberLoginRecordService;
import com.google.gson.Gson;

@EnableBinding(MemberLoginRecordSink.class)
public class MemberLoginRecordMsgReceiver {

	@Autowired
	private MemberLoginRecordService memberLoginRecordService;

	private Gson gson = new Gson();

	@StreamListener(MemberLoginRecordSink.MEMBERLOGINRECORD)
	public void memberLoginRecord(CommonMO mo) {
		String json = gson.toJson(mo.getData());
		String msg = mo.getMsg();
		MemberLoginRecord record = gson.fromJson(json, MemberLoginRecord.class);
		if ("member login".equals(msg)) {
			memberLoginRecordService.save(record);
		}
		if ("update member online".equals(msg)) {
			memberLoginRecordService.updateOnlineTimeById(record.getId(), record.getOnlineTime());
		}
	}
}
