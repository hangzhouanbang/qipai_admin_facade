package com.anbang.qipai.admin.msg.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.MemberGradeSink;
import com.anbang.qipai.admin.plan.domain.Grade.MemberGrade;
import com.anbang.qipai.admin.plan.service.MemberGradeService;

import net.sf.json.JSONObject;

/**接收会员等级
 * @author 程佳 2018.6.22
 * **/
@EnableBinding(MemberGradeSink.class)
public class MemberGradeMsgReceiver {
	
	@Autowired
	private MemberGradeService memberGradeService;

	@StreamListener(MemberGradeSink.grade)
	public void insert_grade(Object payload) {
		JSONObject json = JSONObject.fromObject(payload);
		JSONObject obj = (JSONObject) json.get("data");
		MemberGrade memberGrade = (MemberGrade) JSONObject.toBean(obj,MemberGrade.class);
		memberGradeService.insert_grade(memberGrade);
	}
}
