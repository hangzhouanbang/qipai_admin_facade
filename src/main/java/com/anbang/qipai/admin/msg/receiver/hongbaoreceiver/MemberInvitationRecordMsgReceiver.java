package com.anbang.qipai.admin.msg.receiver.hongbaoreceiver;

import com.anbang.qipai.admin.msg.channel.sink.MemberInvitationRecordSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.hongbao.MemberInvitationRecord;
import com.anbang.qipai.admin.plan.dao.hongbaodao.MemberInvitationRecordDao;
import com.anbang.qipai.admin.plan.service.MemberInvitationRecordService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(MemberInvitationRecordSink.class)
public class MemberInvitationRecordMsgReceiver {

    @Autowired
    private MemberInvitationRecordDao memberInvitationRecordDao;

    private Gson gson = new Gson();

    @StreamListener(MemberInvitationRecordSink.MEMBERINVITATIONRECORD)
    public void memberClubCard(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        if ("new record".equals(msg)) {
            MemberInvitationRecord record = gson.fromJson(json, MemberInvitationRecord.class);
            memberInvitationRecordDao.save(record);
        }
        if ("update record".equals(msg)) {
            MemberInvitationRecord record = gson.fromJson(json, MemberInvitationRecord.class);
            memberInvitationRecordDao.save(record);
        }
    }
}
