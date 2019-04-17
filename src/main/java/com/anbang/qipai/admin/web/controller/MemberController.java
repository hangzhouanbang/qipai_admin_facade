package com.anbang.qipai.admin.web.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.anbang.qipai.admin.constant.Constants;
import com.anbang.qipai.admin.plan.bean.agents.AgentDbo;
import com.anbang.qipai.admin.plan.service.agentsservice.AgentDboService;
import com.anbang.qipai.admin.plan.service.gameservice.GameHistoricalJuResultService;
import com.anbang.qipai.admin.remote.service.QipaiXiuxianchangRemoteService;
import com.anbang.qipai.admin.util.CommonVOUtil;
import com.anbang.qipai.admin.util.enums.CommonVOStatusEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anbang.qipai.admin.cqrs.c.service.AdminAuthService;
import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.bean.members.MemberLoginLimitRecord;
import com.anbang.qipai.admin.plan.bean.members.MemberLoginRecord;
import com.anbang.qipai.admin.plan.bean.members.MemberOperationRecord;
import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberGoldService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberLoginLimitRecordService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberLoginRecordService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberOperationRecordService;
import com.anbang.qipai.admin.plan.service.membersservice.MemberScoreService;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;
import com.anbang.qipai.admin.remote.service.QipaiGameRemoteService;
import com.anbang.qipai.admin.remote.service.QipaiMembersRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.anbang.qipai.admin.web.vo.membersvo.MemberVO;
import com.highto.framework.web.page.ListPage;

/**
 * 会员controller
 *
 * @author 林少聪 2018.6.4
 */
@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminAuthService adminAuthService;

    @Autowired
    private MemberDboService memberService;

    @Autowired
    private QipaiMembersRemoteService qipaiMembersRemoteService;

    @Autowired
    private QipaiGameRemoteService qipaiGameRemoteService;

    @Autowired
    private MemberGoldService memberGoldService;

    @Autowired
    private MemberScoreService memberScoreService;

    @Autowired
    private MemberLoginRecordService memberLoginRecordService;

    @Autowired
    private MemberLoginLimitRecordService memberLoginLimitRecordService;

    @Autowired
    private MemberOperationRecordService memberOperationRecordService;

    @Autowired
    private AgentDboService agentDboService;

    @Autowired
    private GameHistoricalJuResultService gameHistoricalJuResultService;

    @Autowired
    private QipaiXiuxianchangRemoteService qipaiXiuxianchangRemoteService;

    /**
     * 查询用户
     *
     * @param page
     * @param size
     * @param member
     * @return
     */
    @RequestMapping(value = "/querymember", method = RequestMethod.POST)
    public CommonVO queryMember(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size, MemberVO member) {
        CommonVO vo = new CommonVO();
        Map data = new HashMap();
        ListPage listPage = memberService.findMemberDboByConditions(page, size, member);
        data.put("memberList", listPage);

        //是否为机器人玩家
        if (member.isRobot() == true) {
            long amount = memberService.countRobotAmount();
            data.put("amount", amount);
            long vipAmount = memberService.countRobotVipMember();
            data.put("vipAmount", vipAmount);
            long noVipAmount = amount - vipAmount;
            data.put("noVipAmount", noVipAmount);
        } else {
            long amount = memberService.countAmount();
            data.put("amount", amount);
            long vipAmount = memberService.countVipMember();
            data.put("vipAmount", vipAmount);
            long noVipAmount = amount - vipAmount;
            data.put("noVipAmount", noVipAmount);
        }
        vo.setSuccess(true);
        vo.setMsg("memberList");
        vo.setData(data);
        return vo;
    }

    @RequestMapping(value = "/querymemberdetail", method = RequestMethod.POST)
    public CommonVO queryMemberDetail(String memberId) {
        CommonVO vo = new CommonVO();
        Map data = new HashMap();
        MemberDbo memberDbo = memberService.findMemberById(memberId);
        data.put("agentId", memberDbo.getAgentId());
        data.put("realName", memberDbo.getRealName());
        data.put("gender", memberDbo.getGender());
        data.put("phone", memberDbo.getPhone());
        data.put("IDcard", memberDbo.getIdCard());
        data.put("cost", memberDbo.getCost());

        data.put("nickname", memberDbo.getNickname());
        data.put("id", memberDbo.getId());
        data.put("gold", memberDbo.getGold());
        data.put("score", memberDbo.getScore());
        data.put("vip", memberDbo.isVip());
        data.put("vipEndTime", memberDbo.getVipEndTime());

        long onlineTime = 0;
        String loginIp = "";
        String loginTime = "";
        MemberLoginRecord loginRecord = memberLoginRecordService.findRecentRecordByMemberId(memberId);
        if (loginRecord != null) {
            onlineTime = loginRecord.getOnlineTime() / 60000;
            loginIp = loginRecord.getLoginIp();
            loginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(loginRecord.getLoginTime()));
        }
        data.put("onlineTime", onlineTime + "m");
        data.put("loginIp", loginIp);
        data.put("ipAddress", memberDbo.getIpAddress());
        data.put("loginTime", loginTime);
        CommonRemoteVO rvo = qipaiGameRemoteService.game_queryMemberPlayingRoom(memberId);
        data.put("roomList", rvo.getData());

        //查询历史战绩
        ListPage listPage = gameHistoricalJuResultService.findGameHistoricalResultByMemberId(Constants.PageSetup.DEFAULT_PAGE, Constants.PageSetup.DEFAULT_SIZE, memberId);
        data.put("recordList", listPage);

        vo.setSuccess(true);
        vo.setMsg("member detail");
        vo.setData(data);
        return vo;
    }

    /**
     * 移除推广员绑定
     */
    @RequestMapping(value = "/remove_agent_bind", method = RequestMethod.POST)
    public CommonVO removeAgentBind(String token, String memberId) {
        CommonVO vo = new CommonVO();
        CommonRemoteVO rvo = qipaiMembersRemoteService.remove_agentbind(memberId);
        if (rvo.isSuccess()) {
            String adminId = adminAuthService.getAdminIdBySessionId(token);
            Admin admin = adminService.findAdminById(adminId);
            MemberDbo member = memberService.findMemberById(memberId);
            MemberOperationRecord record = new MemberOperationRecord(member);
            record.setDesc("移除推广员绑定");
            record.setOperator(admin.getNickname());
            memberOperationRecordService.save(record);
        }
        vo.setSuccess(rvo.isSuccess());
        vo.setMsg(rvo.getMsg());
        return vo;
    }

    /**
     * 修改推广员绑定
     *
     * @param token
     * @param memberId
     * @param agentId
     * @return
     */
    @RequestMapping(value = "/update_agent_bind", method = RequestMethod.POST)
    public CommonVO updateAgentBind(String token, String memberId, String agentId) {
        //校验token
        if (adminAuthService.getAdminIdBySessionId(token) == null) {
            return CommonVOUtil.error(CommonVOStatusEnum.TOKEN_NOT_EXIST.getMsg());
        }

        //校验memberId
        if (memberService.findMemberById(memberId) == null) {
            return CommonVOUtil.error(CommonVOStatusEnum.MEMBERID_NOT_EXIST.getMsg());
        }

        //校验agentId
        AgentDbo agentDbo = agentDboService.findAgentDboById(agentId);
        if (agentDbo == null) {
            return CommonVOUtil.error(CommonVOStatusEnum.AGENTID_NOT_EXIST.getMsg());
        }

        CommonRemoteVO rvo = qipaiMembersRemoteService.update_agentbind(memberId, agentId);
        if (rvo.isSuccess()) {
            String adminId = adminAuthService.getAdminIdBySessionId(token);
            Admin admin = adminService.findAdminById(adminId);

            MemberDbo member = memberService.findMemberById(memberId);
            MemberOperationRecord record = new MemberOperationRecord(member);

            record.setDesc("修改推广员绑定");
            record.setOperator(admin.getNickname());
            memberOperationRecordService.save(record);
        }
        return CommonVOUtil.success(rvo.isSuccess(), rvo.getMsg());
    }

    /**
     * 批量赠送玉石
     **/
    @RequestMapping(value = "/give_reward_gold", method = RequestMethod.POST)
    public CommonVO giveGold(@RequestParam(value = "id") String[] ids, Integer amount, String token) {
        CommonVO vo = new CommonVO();
        CommonRemoteVO rvo = new CommonRemoteVO();
        if (ids.length == 0) {
            rvo.setSuccess(true);
        } else {
            rvo = qipaiMembersRemoteService.gold_givegoldtomembers(ids, amount, "admin_give_gold");
        }
        if (rvo.isSuccess()) {
            String adminId = adminAuthService.getAdminIdBySessionId(token);
            Admin admin = adminService.findAdminById(adminId);
            List<MemberDbo> memberList = memberService.findMemberDboByIds(ids);
            for (MemberDbo m : memberList) {
                MemberOperationRecord record = new MemberOperationRecord(m);
                record.setDesc("赠送玉石");
                record.setOperator(admin.getNickname());
                record.setParam(amount.toString());
                memberOperationRecordService.save(record);
            }
        }
        vo.setSuccess(rvo.isSuccess());
        vo.setMsg(rvo.getMsg());
        return vo;
    }

    /**
     * 批量赠送礼券
     **/
    @RequestMapping(value = "/give_reward_score", method = RequestMethod.POST)
    public CommonVO giveScore(@RequestParam(value = "id") String[] ids, Integer amount, String token) {
        CommonVO vo = new CommonVO();
        CommonRemoteVO rvo = new CommonRemoteVO();
        if (ids.length == 0) {
            rvo.setSuccess(true);
        } else {
            rvo = qipaiMembersRemoteService.score_givescoretomembers(ids, amount, "admin_give_score");
        }
        if (rvo.isSuccess()) {
            String adminId = adminAuthService.getAdminIdBySessionId(token);
            Admin admin = adminService.findAdminById(adminId);
            List<MemberDbo> memberList = memberService.findMemberDboByIds(ids);
            for (MemberDbo m : memberList) {
                MemberOperationRecord record = new MemberOperationRecord(m);
                record.setDesc("赠送礼券");
                record.setOperator(admin.getNickname());
                record.setParam(amount.toString());
                memberOperationRecordService.save(record);
            }
        }
        vo.setSuccess(rvo.isSuccess());
        vo.setMsg(rvo.getMsg());
        return vo;
    }


    @RequestMapping(value = "/give_reward_clubcard", method = RequestMethod.POST)
    public CommonVO give_reward_clubcard(@RequestParam(value = "id") String[] ids, Integer vipTime, String token) {
        CommonVO vo = new CommonVO();
        String param = "";
        switch (vipTime) {
            case 1:
                param = "日卡";
                break;
            case 7:
                param = "周卡";
                break;
            case 30:
                param = "月卡";
                break;
            case 90:
                param = "季卡";
                break;
            default:
                vo.setSuccess(false);
                vo.setMsg("invalid vipTime");
                return vo;
        }
        long day = 24L * 60 * 60 * 1000;
        CommonRemoteVO rvo = qipaiMembersRemoteService.give_viptime(ids, vipTime * day);
        if (rvo.isSuccess()) {
            String adminId = adminAuthService.getAdminIdBySessionId(token);
            Admin admin = adminService.findAdminById(adminId);
            List<MemberDbo> memberList = memberService.findMemberDboByIds(ids);
            for (MemberDbo m : memberList) {
                MemberOperationRecord record = new MemberOperationRecord(m);
                record.setDesc("赠送会员卡");
                record.setOperator(admin.getNickname());
                record.setParam(param);
                memberOperationRecordService.save(record);
            }
        }
        vo.setSuccess(rvo.isSuccess());
        vo.setMsg(rvo.getMsg());
        return vo;
    }

    /**
     * 查询会员金币流水
     *
     * @param page
     * @param size
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/querygoldrecord", method = RequestMethod.POST)
    public CommonVO queryGoldRecord(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size, String memberId) {
        CommonVO vo = new CommonVO();
        ListPage listPage = memberGoldService.findGoldRecordByMemberId(page, size, memberId);
        vo.setSuccess(true);
        vo.setMsg("goldrecordList");
        vo.setData(listPage);
        return vo;
    }


    /**
     * 查询会员积分流水
     *
     * @param page
     * @param size
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/queryscorerecord", method = RequestMethod.POST)
    public CommonVO queryScoreRecord(@RequestParam(name = "page", defaultValue = "1") int page,
                                     @RequestParam(name = "size", defaultValue = "10") int size, String memberId) {
        CommonVO vo = new CommonVO();
        ListPage listPage = memberScoreService.findScoreRecordByMemberId(page, size, memberId);
        vo.setSuccess(true);
        vo.setMsg("goldrecordList");
        vo.setData(listPage);
        return vo;
    }

    /**
     * 查询操作记录
     */
    @RequestMapping(value = "/queryoperation", method = RequestMethod.POST)
    public CommonVO queryOperation(@RequestParam(name = "page", defaultValue = "1") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size, MemberOperationRecord record) {
        CommonVO vo = new CommonVO();
        ListPage listPage = memberOperationRecordService.findByConditions(page, size, record);
        vo.setSuccess(true);
        vo.setMsg("operationrecordList");
        vo.setData(listPage);
        return vo;
    }

    @RequestMapping(value = "/querylimit", method = RequestMethod.POST)
    public CommonVO queryLimit(@RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "size", defaultValue = "10") int size, String memberId) {
        CommonVO vo = new CommonVO();
        Map data = new HashMap<>();
        ListPage listPage = memberLoginLimitRecordService.findMemberLoginLimitRecordByMemberId(page, size, memberId);
        data.put("listPage", listPage);
        vo.setSuccess(true);
        vo.setMsg("limit records");
        vo.setData(data);
        return vo;
    }

    @RequestMapping(value = "/addlimit", method = RequestMethod.POST)
    public CommonVO addLimit(MemberLoginLimitRecord record) {
        CommonVO vo = new CommonVO();
        MemberDbo memberDbo = memberService.findMemberById(record.getMemberId());
        if (memberDbo == null) {
            vo.setSuccess(false);
            vo.setMsg("invalid memberId");
            return vo;
        }
        record.setEfficient(true);
        record.setNickname(memberDbo.getNickname());
        record.setCreateTime(System.currentTimeMillis());
        qipaiMembersRemoteService.addlimit(record);
        vo.setSuccess(true);
        return vo;
    }

    @RequestMapping(value = "/deletelimits", method = RequestMethod.POST)
    public CommonVO deleteLimits(@RequestParam(value = "recordId") String[] recordIds) {
        CommonVO vo = new CommonVO();
        qipaiMembersRemoteService.deletelimits(recordIds);
        vo.setSuccess(true);
        return vo;
    }

    @PostMapping("/giveXiuxianGold")
    public CommonVO giveXiuxianGold(String memberId, int amount, String textSummary) {

        if (StringUtils.isBlank(memberId) || amount == 0 || StringUtils.isBlank(textSummary)) {
            return CommonVOUtil.lackParameter();
        }

        CommonRemoteVO rvo = qipaiXiuxianchangRemoteService.giveGoldToMember(memberId, amount, textSummary);
        if (rvo.isSuccess()) {
            return CommonVOUtil.success("success");
        }

        return CommonVOUtil.systemException();
    }

    @PostMapping("/findMemberByIP")
    public CommonVO findMemberByIP(String loginIp) {
        if (StringUtils.isBlank(loginIp)) {
            return CommonVOUtil.success("loginIp is null");
        }
        List<MemberDbo> memberDbos = memberService.findMemberByIP(loginIp);
        return CommonVOUtil.success(memberDbos, "success");
    }

    /**
     * 根据注册IP查询玩家
     */
    @PostMapping("/findMemberByReqIP")
    public CommonVO findMemberByReqIP(String reqIP) {
        if (StringUtils.isBlank(reqIP)) {
            return CommonVOUtil.success("reqIP is null");
        }
        List<MemberDbo> memberDbos = memberService.findMemberByReqIP(reqIP);
        return CommonVOUtil.success(memberDbos, "success");
    }
}
