package com.anbang.qipai.admin.msg.receiver.gamereceiver;

import com.alibaba.fastjson.JSON;
import com.anbang.qipai.admin.constant.LotteryType;
import com.anbang.qipai.admin.msg.msjobj.*;
import com.anbang.qipai.admin.plan.bean.ObatinSigningPrizeRecord;
import com.anbang.qipai.admin.plan.bean.PrizeEnum;
import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.bean.members.MemberGoldRecordDbo;
import com.anbang.qipai.admin.plan.bean.members.MemberScoreRecordDbo;
import com.anbang.qipai.admin.plan.bean.signin.PhoneFeeRecord;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberGoldService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberScoreService;
import com.anbang.qipai.admin.plan.service.signinservice.*;
import com.anbang.qipai.admin.remote.LotteryMoTypeEnum;
import com.dml.accounting.AccountingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.sink.SignInPrizeLogSink;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrizeExchangeLog;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrizeLog;
import com.google.gson.Gson;

import static com.anbang.qipai.admin.msg.channel.sink.SignInPrizeLogSink.*;

/**
 * 接受中奖记录消息
 *
 * @author created by hanzhuofan  2018.09.18
 */
@EnableBinding(SignInPrizeLogSink.class)
public class SignInPrizeLogMsgReceiver {

    @Autowired
    private SignInPrizeService signInPrizeService;

    @Autowired
    private SignInPrizeLogService signInPrizeLogService;

    @Autowired
    private SignInPrizeExchangeLogService signInPrizeExchangeLogService;

    @Autowired
    private ObtainSignPrizeRecordService obtainSignPrizeRecordService;

    @Autowired
    private MemberGoldService memberGoldService;

    @Autowired
    private MemberScoreService memberScoreService;

    @Autowired
    private MemberDboService memberService;

    private Gson gson = new Gson();

    @StreamListener(SignInPrizeLogSink.channel)
    public void addSignInPrizeLog(CommonMO mo) {
        System.out.println(">>>监听到消息:" + mo);
        final String msg = mo.getMsg();
        final String dataJson = gson.toJson(mo.getData());
        if (RAFFLE_RECORD.equals(msg)) {
            this.handleRaffle(dataJson);
        } else if (OBTAIN_SIGN_PRIZE.equals(msg)) {
            this.handleObtainSigningPrize(dataJson);
        } else if (PRIZE_EXCHANGE.equals(msg)) {
            this.handleExchange(dataJson);
        }
    }

    public void handleObtainSigningPrize(String dataJson) {
        ObtainSigningPrizeRecordMo obtainSigningPrizeRecordMo = JSON.parseObject(dataJson, ObtainSigningPrizeRecordMo.class);
        ObatinSigningPrizeRecord obatinSigningPrizeRecord = new ObatinSigningPrizeRecord();
        obatinSigningPrizeRecord.setId(obtainSigningPrizeRecordMo.getId());
        obatinSigningPrizeRecord.setMemberId(obtainSigningPrizeRecordMo.getMemberId());
        obatinSigningPrizeRecord.setObtainTime(obtainSigningPrizeRecordMo.getObtainTime());
        obatinSigningPrizeRecord.setPrize(obtainSigningPrizeRecordMo.getPrize());
        obatinSigningPrizeRecord.setScore(obtainSigningPrizeRecordMo.getScore());
        obatinSigningPrizeRecord.setVipLevel(obtainSigningPrizeRecordMo.getVipLevel());
        obatinSigningPrizeRecord.setGoldenAccountingNo(obtainSigningPrizeRecordMo.getGoldAccountingRecord().getAccountingNo());
        obatinSigningPrizeRecord.setScoreAccountingNo(obtainSigningPrizeRecordMo.getScoreAccountingRecord().getAccountingNo());
        obtainSignPrizeRecordService.save(obatinSigningPrizeRecord);
        PrizeEnum prize = obatinSigningPrizeRecord.getPrize();
        if (PrizeEnum.isGoldType(prize)) {
            MemberGoldRecordDbo goldRecordDbo = new MemberGoldRecordDbo();
            AccountingRecord accountingRecord = obtainSigningPrizeRecordMo.getGoldAccountingRecord();
            goldRecordDbo.setAccountId(accountingRecord.getAccountId());
            goldRecordDbo.setAccountingNo(accountingRecord.getAccountingNo());
            goldRecordDbo.setAccountingAmount((int) accountingRecord.getAccountingAmount());
            goldRecordDbo.setAccountingTime(accountingRecord.getAccountingTime());
            goldRecordDbo.setBalanceAfter((int) accountingRecord.getBalanceAfter());
            goldRecordDbo.setSummary(accountingRecord.getSummary());
            goldRecordDbo.setMemberId(obtainSigningPrizeRecordMo.getMemberId());
            memberGoldService.addGoldRecord(goldRecordDbo);
        } else if (PrizeEnum.isMemberCardType(prize)) {

        }
        if (obtainSigningPrizeRecordMo.getScoreAccountingRecord() != null) {
            AccountingRecord scoreRecord = obtainSigningPrizeRecordMo.getScoreAccountingRecord();
            MemberScoreRecordDbo memberScoreRecordDbo = new MemberScoreRecordDbo();
            memberScoreRecordDbo.setAccountId(scoreRecord.getAccountId());
            memberScoreRecordDbo.setAccountingAmount((int) scoreRecord.getAccountingAmount());
            memberScoreRecordDbo.setBalanceAfter((int) scoreRecord.getBalanceAfter());
            memberScoreRecordDbo.setAccountingNo(scoreRecord.getAccountingNo());
            memberScoreRecordDbo.setAccountingTime(scoreRecord.getAccountingTime());
            memberScoreRecordDbo.setMemberId(obatinSigningPrizeRecord.getMemberId());
            memberScoreRecordDbo.setSummary(scoreRecord.getSummary());
            memberScoreService.addScoreRecord(memberScoreRecordDbo);
        }
    }

    public void handleRaffle(String dataJson) {
        MemberRaffleHistoryMo memberRaffleHistoryMo = JSON.parseObject(dataJson, MemberRaffleHistoryMo.class);
        SignInPrizeLog signInPrizeLog = new SignInPrizeLog();
        signInPrizeLog.setId(memberRaffleHistoryMo.getId());
        signInPrizeLog.setCreateTime(memberRaffleHistoryMo.getTime());
        final LotteryMo lotteryMo = memberRaffleHistoryMo.getLottery();

        final String id = lotteryMo.getId();
        signInPrizeService.decreaseStoreById(id);

        signInPrizeLog.setName(lotteryMo.getName());
        signInPrizeLog.setSignInPrizeId(memberRaffleHistoryMo.getId());
        signInPrizeLog.setSingleNum(lotteryMo.getSingleNum());
        final String type = LotteryMoTypeEnum.of(lotteryMo.getType());
        signInPrizeLog.setType(type);
        signInPrizeLog.setMemberId(memberRaffleHistoryMo.getMemberId());
        signInPrizeLogService.addSignInPrizeLog(signInPrizeLog);
        signInPrizeService.decreaseStoreById(memberRaffleHistoryMo.getLottery().getId());
        if (LotteryType.exchangeAble(type)) {
            SignInPrizeExchangeLog signInPrizeExchangeLog = new SignInPrizeExchangeLog();
            MemberDbo memberDbo = memberService.findMemberById(memberRaffleHistoryMo.getMemberId());
            signInPrizeExchangeLog.setNickName(memberDbo.getNickname());
            signInPrizeExchangeLog.setMemberId(memberDbo.getId());
            signInPrizeExchangeLog.setPrizeName(lotteryMo.getName());
            signInPrizeExchangeLog.setId(memberRaffleHistoryMo.getId());
            signInPrizeExchangeLog.setRewardTime(memberRaffleHistoryMo.getTime());
            signInPrizeExchangeLog.setIssue("0");
            final Address address = memberRaffleHistoryMo.getAddress();
            if (address != null) {
                signInPrizeExchangeLog.setAddress(address.getAddress());
                signInPrizeExchangeLog.setPhone(address.getPhone());
                signInPrizeExchangeLog.setDeliveryName(address.getName());
            }
            signInPrizeExchangeLogService.addSignInPrizeExchangeLog(signInPrizeExchangeLog);
        }
    }

    public void handleExchange(String dataJson) {

        ScoreExchangeMo scoreExchangeMo = JSON.parseObject(dataJson, ScoreExchangeMo.class);
        SignInPrizeExchangeLog signInPrizeExchangeLog = new SignInPrizeExchangeLog();
        signInPrizeExchangeLog.setId(scoreExchangeMo.getId());
        signInPrizeExchangeLog.setPhone(scoreExchangeMo.getPhone());
        signInPrizeExchangeLog.setIssue("0");
        signInPrizeExchangeLog.setRewardTime(scoreExchangeMo.getTime());
        if (scoreExchangeMo.getExchangeTypeEnum() == ExchangeTypeEnum.HONG_BAO) {
            String prizeName = scoreExchangeMo.getScore() + "红包点兑换" + scoreExchangeMo.getCurrency() + "红包";
            signInPrizeExchangeLog.setPrizeName(prizeName);

        } else if (scoreExchangeMo.getExchangeTypeEnum() == ExchangeTypeEnum.PHONE_FEE) {
            String prizeName = scoreExchangeMo.getScore() + "话费点兑换" + scoreExchangeMo.getCurrency() + "话费";
            signInPrizeExchangeLog.setPrizeName(prizeName);
        }
        signInPrizeExchangeLog.setScore(scoreExchangeMo.getScore());
        signInPrizeExchangeLog.setCurrency(scoreExchangeMo.getCurrency());
        signInPrizeExchangeLogService.addSignInPrizeExchangeLog(signInPrizeExchangeLog);
    }

}
