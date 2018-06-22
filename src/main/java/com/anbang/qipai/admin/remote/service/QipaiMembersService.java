package com.anbang.qipai.admin.remote.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anbang.qipai.admin.plan.domain.ClubCard;
import com.anbang.qipai.admin.plan.domain.Grade.MemberGrade;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;

/**
 * 设置金币服务接口
 * 
 * @author 程佳 2018.5.31 声明这是一个Feign客户端，并且指明服务ID
 **/
@FeignClient("qipai-members")
public interface QipaiMembersService {

	/**
	 * 这里定义了类似于SpringMVC用法的方法，就可以进行RESTful的调用了
	 **/
	@RequestMapping(value = "/conf/commonuser")
	public CommonRemoteVO commonuser(@RequestParam(value = "signGoldNumber") Integer signGoldNumber,
			@RequestParam(value = "goldForNewNember") Integer goldForNewNember,
			@RequestParam(value = "inviteIntegralNumber") Integer inviteIntegralNumber,
			@RequestParam(value = "planGrowIntegralSpeed") float planGrowIntegralSpeed);

	@RequestMapping(value = "/conf/vipuser")
	public CommonRemoteVO vipuser(@RequestParam(value = "signGoldNumber") Integer signGoldNumber,
			@RequestParam(value = "inviteIntegralNumber") Integer inviteIntegralNumber,
			@RequestParam(value = "vipGrowIntegralSpeed") float vipGrowIntegralSpeed,
			@RequestParam(value = "vipGrowGradeSpeed") float vipGrowGradeSpeed);

	@RequestMapping(value = "/clubcard/addclubcard")
	public CommonRemoteVO clubcard_add(@RequestBody ClubCard clubCard);

	@RequestMapping(value = "/clubcard/deleteclubcards")
	public CommonRemoteVO clubcard_delete(@RequestBody String[] clubCardIds);

	@RequestMapping(value = "/clubcard/updateclubcard")
	public CommonRemoteVO clubcard_update(@RequestBody ClubCard clubCard);
	
	@RequestMapping(value = "/grade/insert_grade")
	public CommonRemoteVO grade_insert_grade(@RequestBody MemberGrade memberGrade);
}
