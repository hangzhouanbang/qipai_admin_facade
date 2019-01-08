package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.bean.grade.MemberGrade;
import com.anbang.qipai.admin.plan.bean.members.MemberLoginLimitRecord;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

/**
 * 设置金币服务接口
 * 
 * @author 程佳 2018.5.31 声明这是一个Feign客户端，并且指明服务ID
 **/
@FeignClient("qipai-members")
public interface QipaiMembersRemoteService {

	/**
	 * 这里定义了类似于SpringMVC用法的方法，就可以进行RESTful的调用了
	 **/
	@RequestMapping(value = "/conf/commonuser")
	public CommonRemoteVO commonuser(@RequestParam(value = "signGoldNumber") Integer signGoldNumber,
			@RequestParam(value = "goldForNewNember") Integer goldForNewNember,
			@RequestParam(value = "inviteIntegralNumber") Integer inviteIntegralNumber,
			@RequestParam(value = "goldForAgentInvite") Integer goldForAgentInvite,
			@RequestParam(value = "planGrowIntegralSpeed") float planGrowIntegralSpeed);

	@RequestMapping(value = "/conf/vipuser")
	public CommonRemoteVO vipuser(@RequestParam(value = "signGoldNumber") Integer signGoldNumber,
			@RequestParam(value = "inviteIntegralNumber") Integer inviteIntegralNumber,
			@RequestParam(value = "vipGrowIntegralSpeed") float vipGrowIntegralSpeed,
			@RequestParam(value = "vipGrowGradeSpeed") float vipGrowGradeSpeed);

	@RequestMapping(value = "/grade/insert_grade")
	public CommonRemoteVO grade_insert_grade(@RequestBody MemberGrade memberGrade);

	@RequestMapping(value = "/member/update_viptime")
	public CommonRemoteVO give_viptime(@RequestBody String[] ids, @RequestParam(value = "vipEndTime") Long vipEndTime);


	@RequestMapping(value = "/member/update_viptime_id")
	public CommonRemoteVO give_viptime_id(@RequestBody String id, @RequestParam(value = "vipEndTime") Long vipEndTime);


	@RequestMapping(value = "/auth/addlimit")
	public CommonRemoteVO addlimit(@RequestBody MemberLoginLimitRecord record);

	@RequestMapping(value = "/auth/deletelimits")
	public CommonRemoteVO deletelimits(@RequestBody String[] recordIds);

	@RequestMapping(value = "/snapshot/save")
	CommonRemoteVO snapshot_save();

	@RequestMapping(value = "/gold/members_withdraw")
	public CommonRemoteVO gold_members_withdraw(@RequestBody String[] memberIds,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping(value = "/gold/givegoldtomembers")
	public CommonRemoteVO gold_givegoldtomembers(@RequestBody String[] memberIds,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping(value = "/score/memebrs_withdraw")
	public CommonRemoteVO score_memebrs_withdraw(@RequestBody String[] memberIds,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping(value = "/score/givescoretomembers")
	public CommonRemoteVO score_givescoretomembers(@RequestBody String[] memberIds,
			@RequestParam(value = "amount") int amount, @RequestParam(value = "textSummary") String textSummary);

	@RequestMapping(value = "/member/removeagent")
	public CommonRemoteVO remove_agentbind(@RequestParam(value = "memberId") String memberId);

	@RequestMapping(value = "/member/updateagent")
	public CommonRemoteVO update_agentbind(@RequestParam(value = "memberId") String memberId,
			@RequestParam(value = "agentId") String agentId);
}
