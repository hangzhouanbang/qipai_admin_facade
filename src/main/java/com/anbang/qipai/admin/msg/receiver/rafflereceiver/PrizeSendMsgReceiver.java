package com.anbang.qipai.admin.msg.receiver.rafflereceiver;

import com.alibaba.fastjson.JSON;
import com.anbang.qipai.admin.msg.channel.sink.PrizeSendSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.juprize.JuPrizeRecord;
import com.anbang.qipai.admin.plan.bean.juprize.JuPrizeRecordDetail;
import com.anbang.qipai.admin.plan.bean.juprize.JuPrizeTypeEnum;
import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.dao.juprizedao.JuPrizeRecordDetailDao;
import com.anbang.qipai.admin.plan.dao.membersdao.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


/**
 * @Description:
 */
@EnableBinding(PrizeSendSink.class)
public class PrizeSendMsgReceiver {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private JuPrizeRecordDetailDao juPrizeRecordDetailDao;

    Logger logger = LoggerFactory.getLogger(getClass());

    @StreamListener(PrizeSendSink.PRIZESEND)
    public void prizeSend(CommonMO mo) {
        String msg = mo.getMsg();
        String json = JSON.toJSONString(mo.getData());
        try {
            if ("JuPrizeRecord".equals(msg)) {
                JuPrizeRecord record = JSON.parseObject(json, JuPrizeRecord.class);
                MemberDbo memberDbo = memberDao.findMemberById(record.getPlayerId());
                if (memberDbo != null) {
                    JuPrizeRecordDetail prizeDetail = new JuPrizeRecordDetail();
                    prizeDetail.setId(record.getId());
                    prizeDetail.setPlayerId(record.getPlayerId());
                    prizeDetail.setNickName(memberDbo.getNickname());
                    prizeDetail.setPhone(memberDbo.getPhone());
                    prizeDetail.setPrizeType(JuPrizeTypeEnum.getJuPrizeName(record.getJuPrize().getPrizeType()));
                    prizeDetail.setSingleNum(record.getJuPrize().getSingleNum());
                    prizeDetail.setLoginIp(memberDbo.getLoginIp());
                    prizeDetail.setIpAddress(memberDbo.getIpAddress());
                    prizeDetail.setSendTime(record.getSendTime());
                    prizeDetail.setDrawType(record.getJuPrize().getDrawType().name());
                    juPrizeRecordDetailDao.save(prizeDetail);
                }
            }
        } catch (Exception e) {
            logger.error("prizeSend" + json + JSON.toJSONString(e));
        }
    }
}
