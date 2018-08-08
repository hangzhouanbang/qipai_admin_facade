package com.anbang.qipai.admin.msg.receiver.tasksreceiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.anbang.qipai.admin.msg.channel.taskschannel.TasksSink;
import com.anbang.qipai.admin.msg.msjobj.CommonMO;
import com.anbang.qipai.admin.plan.bean.tasks.TaskDocumentHistory;
import com.anbang.qipai.admin.plan.service.tasksservice.TaskDocumentHistoryService;
import com.google.gson.Gson;

@EnableBinding(TasksSink.class)
public class TasksMsgReceiver {

	@Autowired
	private TaskDocumentHistoryService taskDocumentHistoryService;

	private Gson gson = new Gson();

	@StreamListener(TasksSink.TASKS)
	public void tasks(CommonMO mo) {
		String msg = mo.getMsg();
		String json = gson.toJson(mo.getData());
		if ("release task".equals(msg)) {
			TaskDocumentHistory taskHistory = gson.fromJson(json, TaskDocumentHistory.class);
			taskDocumentHistoryService.addTaskDocumentHistory(taskHistory);
		}
		if ("withdraw task".equals(msg)) {
			String[] taskIds = gson.fromJson(json, String[].class);
			taskDocumentHistoryService.withdrawTaskDocumentHistory(taskIds);
		}
	}
}
