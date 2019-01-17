package com.anbang.qipai.admin.msg.receiver.tasksreceiver;

import com.anbang.qipai.admin.msg.channel.sink.FinishTaskSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.msg.msjobj.dbo.FinishedTask;
import com.anbang.qipai.admin.plan.bean.members.MemberDbo;
import com.anbang.qipai.admin.plan.bean.tasks.RewardType;
import com.anbang.qipai.admin.plan.bean.tasks.TaskReceiveRecord;
import com.anbang.qipai.admin.plan.service.membersservice.MemberDboService;
import com.anbang.qipai.admin.plan.service.tasksservice.TaskReceiveRecordService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author yins
 * @Description: 红包任务领取记录
 */
@EnableBinding(FinishTaskSink.class)
public class FinishTaskMsgReceiver {

    @Autowired
    private TaskReceiveRecordService taskReceiveRecordService;

    @Autowired
    private MemberDboService memberDboService;

    private Gson gson = new Gson();

    @StreamListener("finishTasks")
    public void activity(CommonMO mo) {
        String msg = mo.getMsg();
        String json = gson.toJson(mo.getData());
        FinishedTask dbo = gson.fromJson(json, FinishedTask.class);
        if ("finish task".equals(msg)) {
            TaskReceiveRecord taskReceiveRecord = new TaskReceiveRecord();
            taskReceiveRecord.setId(dbo.getId());
            taskReceiveRecord.setTaskType(dbo.getTask().getType());
            taskReceiveRecord.setTaskName(dbo.getTask().getName());
            MemberDbo memberDbo = memberDboService.findMemberById(dbo.getMemberId());
            if (memberDbo != null) {
                taskReceiveRecord.setNickName(memberDbo.getNickname());
            }
            taskReceiveRecord.setPlayerId(dbo.getMemberId());
            taskReceiveRecord.setRewardType(RewardType.toMap().get(dbo.getRewardType().name()));
            taskReceiveRecord.setRewardCount((int)dbo.getRewardNum());
            taskReceiveRecord.setLoginIP(dbo.getGetRewardIP());
            taskReceiveRecord.setReceiveTime(dbo.getFinishTime());
            taskReceiveRecordService.addTaskRceiveRecord(taskReceiveRecord);
        }
    }
}
