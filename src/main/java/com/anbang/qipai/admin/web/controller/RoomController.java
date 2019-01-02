package com.anbang.qipai.admin.web.controller;

import com.anbang.qipai.admin.remote.service.QipaiGameRemoteService;
import com.anbang.qipai.admin.web.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 房间管理
 */
@CrossOrigin
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private QipaiGameRemoteService qipaiGameRemoteService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public CommonVO queryRoom(String playerId, String roomNo) {
        CommonVO vo = new CommonVO();

        return vo;
    }
}
