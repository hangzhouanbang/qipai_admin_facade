package com.anbang.qipai.admin.plan.dao.mongodb.mongodbsignIndao;

import com.alibaba.fastjson.JSON;
import com.anbang.qipai.admin.msg.msjobj.ScoreExchangeMo;
import com.anbang.qipai.admin.plan.bean.members.MemberGoldRecordDbo;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrizeExchangeLog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: 吴硕涵
 * @Date: 2018/12/6 2:17 PM
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbSignInPrizeExchangeLogDaoTest {

    @Autowired
    private MongodbSignInPrizeExchangeLogDao dao;


    @Test
    public void addSignInPrizeExchangeLog() throws Exception {
//        dao.addSignInPrizeExchangeLog(new SignInPrizeExchangeLog("xxx"));
    }


    @Test
    public void jsonTest(){
        String json = "{\"id\":\"5c08d481375f8db43a3fc70c\",\"memberId\":\"629773\",\"phone\":\"15958035203\",\"time\":1544082560443,\"score\":-100,\"currency\":5,\"exchangeType\":\"HONG_BAO\",\"rest\":0,\"accountingRecord\":{\"accountId\":\"629773_hongbao_wallet\",\"accountingNo\":3,\"accountingAmount\":-100.0,\"balanceAfter\":0.0,\"summary\":{\"text\":\"红包兑换 * 100\"},\"accountingTime\":1544082560443}}";
        ScoreExchangeMo exchangeMo = JSON.parseObject(json,ScoreExchangeMo.class);
        return;
    }

    @Test
    public void queryTest(){
        System.out.println(dao.findBymemberId("778052").getId());
    }



}
