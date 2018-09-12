package com.anbang.qipai.admin.web.controller;

import com.anbang.qipai.admin.remote.service.QipaiGameRemoteService;
import com.anbang.qipai.admin.remote.service.QipaiMembersRemoteService;
import com.anbang.qipai.admin.remote.service.RAMJLogRemoteService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snapshot")
public class SnapshotController {

    @Autowired
    private RAMJLogRemoteService ramjLogRemoteService;

    @Autowired
    private QipaiMembersRemoteService qipaiMembersRemoteService;

    @Autowired
    private QipaiGameRemoteService qipaiGameRemoteService;

    @RequestMapping("/save")
    @ResponseBody
    public CommonVO saveSnapshot() {
        final CommonVO vo = new CommonVO();
        vo.setSuccess(true);
        try {
            ramjLogRemoteService.snapshot_save();
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }
        return vo;
    }


    @RequestMapping("/savemembers")
    @ResponseBody
    public CommonVO saveMembers() {
        final CommonVO vo = new CommonVO();
        vo.setSuccess(true);
        try {
            this.qipaiMembersRemoteService.snapshot_save();
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return vo;
    }

    @RequestMapping("/savegame")
    public CommonVO saveGame() {
        final CommonVO vo = new CommonVO();
        vo.setSuccess(true);
        try {
            this.qipaiGameRemoteService.snapshot_save();
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        return vo;
    }

}
