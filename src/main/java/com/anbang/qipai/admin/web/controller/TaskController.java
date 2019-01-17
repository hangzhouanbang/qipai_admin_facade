package com.anbang.qipai.admin.web.controller;

import com.anbang.qipai.admin.plan.bean.tasks.*;
import com.anbang.qipai.admin.plan.service.tasksservice.*;
import com.anbang.qipai.admin.remote.service.QipaiHongBaoRemoteService;
import com.anbang.qipai.admin.web.query.TaskCommonQuery;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anbang.qipai.admin.cqrs.c.service.AdminAuthService;
import com.anbang.qipai.admin.plan.bean.permission.Admin;
import com.anbang.qipai.admin.plan.service.permissionservice.AdminService;
import com.anbang.qipai.admin.remote.service.QipaiTasksRemoteService;
import com.anbang.qipai.admin.remote.vo.CommonRemoteVO;
import com.anbang.qipai.admin.web.vo.CommonVO;
import com.highto.framework.web.page.ListPage;

/**
 * 任务管理
 *
 * @author 林少聪 2018.8.6
 */
@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskDocumentService taskDocumentService;

    @Autowired
    private TaskDocumentHistoryService taskDocumentHistoryService;

    @Autowired
    private QipaiTasksRemoteService qipaiTasksRemoteService;

    @Autowired
    private AdminAuthService adminAuthService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ExchangeManageService exchangeManageService;

    @Autowired
    private TaskReceiveRecordService taskReceiveRecordService;

    @Autowired
    private ExchangeRecordService exchangeRecordService;

    @Autowired
    private QipaiHongBaoRemoteService qipaiHongBaoRemoteService;

    @Autowired
    private WhiteListService whiteListService;

    /**
     * 查询任务种类、类型
     *
     * @return
     */
    @RequestMapping(value = "/querytaskconfig", method = RequestMethod.POST)
    public CommonVO queryTaskConfig() {
        CommonVO vo = new CommonVO();
        CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.task_querytaskconfig();
        vo.setSuccess(commonRemoteVO.isSuccess());
        vo.setMsg(commonRemoteVO.getMsg());
        vo.setData(commonRemoteVO.getData());
        return vo;
    }

    /**
     * 查询任务类型
     *
     * @return
     */
    @RequestMapping(value = "/querytasktype", method = RequestMethod.POST)
    public CommonVO queryTaskType() {
        CommonVO vo = new CommonVO();
        CommonRemoteVO commonRemoteVO = qipaiTasksRemoteService.task_querytasktype();
        vo.setSuccess(commonRemoteVO.isSuccess());
        vo.setMsg(commonRemoteVO.getMsg());
        vo.setData(commonRemoteVO.getData());
        return vo;
    }

    /**
     * 查询任务档案
     *
     * @param page
     * @param size
     * @param taskDoc
     * @return
     */
    @RequestMapping(value = "/querytaskdocument", method = RequestMethod.POST)
    public CommonVO queryTaskDocument(@RequestParam(name = "page", defaultValue = "1") int page,
                                      @RequestParam(name = "size", defaultValue = "10") int size, TaskDocument taskDoc) {
        CommonVO vo = new CommonVO();
        ListPage listPage = taskDocumentService.findTaskDocuments(page, size, taskDoc);
        vo.setSuccess(true);
        vo.setMsg("TaskDocumentList");
        vo.setData(listPage);
        return vo;
    }

    /**
     * 查询任务发布历史记录
     *
     * @param page
     * @param size
     * @param task
     * @return
     */
    @RequestMapping(value = "/querytaskdocumenthistory", method = RequestMethod.POST)
    public CommonVO queryTaskDocumentHistory(@RequestParam(name = "page", defaultValue = "1") int page,
                                             @RequestParam(name = "size", defaultValue = "10") int size, TaskDocumentHistory task) {
        CommonVO vo = new CommonVO();
        Sort sort = new Sort(new Order(Direction.ASC, "releaseTime"));
        ListPage listPage = taskDocumentHistoryService.queryTaskDocumentHistory(page, size, sort, task);
        vo.setSuccess(true);
        vo.setMsg("TaskList");
        vo.setData(listPage);
        return vo;
    }

    /**
     * 添加任务档案
     *
     * @param taskDoc
     * @return
     */
    @RequestMapping(value = "/addtaskdocument", method = RequestMethod.POST)
    public CommonVO addTaskDocument(TaskDocument taskDoc) {
        CommonVO vo = new CommonVO();
        if (taskDoc.getName() == null || taskDoc.getDesc() == null || taskDoc.getTaskName() == null
                || taskDoc.getType() == null) {
            vo.setSuccess(false);
            vo.setMsg("at least one param is null");
            return vo;
        }
        taskDocumentService.addTaskDocument(taskDoc);
        vo.setSuccess(true);
        vo.setMsg("Add TaskDocument");
        return vo;
    }

    /**
     * 删除任务档案
     *
     * @param taskDocIds
     * @return
     */
    @RequestMapping(value = "/deletetaskdocuments", method = RequestMethod.POST)
    public CommonVO deleteTaskDocuments(@RequestParam(value = "taskDocId") String[] taskDocIds) {
        CommonVO vo = new CommonVO();
        taskDocumentService.deleteTaskDocuments(taskDocIds);
        vo.setSuccess(true);
        vo.setMsg("delete taskdocuments");
        return vo;
    }

    /**
     * 修改任务档案
     *
     * @param taskDoc
     * @return
     */
    @RequestMapping(value = "/updatetaskdocument", method = RequestMethod.POST)
    public CommonVO updateTaskDocument(TaskDocument taskDoc) {
        CommonVO vo = new CommonVO();
        taskDocumentService.updateTaskDocument(taskDoc);
        vo.setSuccess(true);
        vo.setMsg("Update TaskDocument");
        return vo;
    }

    /**
     * 发布任务
     *
     * @param task
     * @return
     */
    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public CommonVO releaseTask(TaskDocumentHistory task, String token) {
        CommonVO vo = new CommonVO();
        String adminId = adminAuthService.getAdminIdBySessionId(token);
        if (adminId == null) {
            vo.setSuccess(false);
            return vo;
        }
        Admin admin = adminService.findAdminById(adminId);
        task.setPromulgator(admin.getNickname());
        TaskDocument taskDoc = taskDocumentService.findTaskDocumentById(task.getTaskDocId());
        if (taskDoc == null) {
            vo.setSuccess(false);
            vo.setMsg("Not Found TaskDocument");
            return vo;
        }
        TaskDocumentHistory taskHistory = taskDocumentHistoryService.releaseTaskDocumentHistory(taskDoc, task);
        CommonRemoteVO rvo = qipaiTasksRemoteService.taskdocument_release(taskHistory);
        vo.setSuccess(rvo.isSuccess());
        return vo;
    }

    /**
     * 撤回任务
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public CommonVO withdrawTask(String[] taskId) {
        CommonVO vo = new CommonVO();
        CommonRemoteVO rvo = qipaiTasksRemoteService.taskdocument_withdraw(taskId);
        vo.setSuccess(rvo.isSuccess());
        return vo;
    }

    /**
     * ------------------ 1/19红包
     */

    @RequestMapping(value = "/query_taskreceiverecords", method = RequestMethod.POST)
    public CommonVO queryTaskReceiveRecords(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "size", defaultValue = "20") int size,
                                            TaskCommonQuery taskCommonQuery) {
        CommonVO vo = new CommonVO();
        ListPage listPage = taskReceiveRecordService.getTaskReceiveRecords(page, size, taskCommonQuery);
        vo.setSuccess(true);
        vo.setMsg("query taskRecord success");
        vo.setData(listPage);
        return vo;

    }

    @RequestMapping(value = "/query_exchangerecords", method = RequestMethod.POST)
    public CommonVO queryExchangeRecords(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "20") int size,
                                         TaskCommonQuery taskCommonQuery) {
        CommonVO vo = new CommonVO();
        ListPage listPage = exchangeRecordService.getExchangeRecords(page, size, taskCommonQuery);
        vo.setSuccess(true);
        vo.setMsg("query exchangeRecord success");
        vo.setData(listPage);
        return vo;
    }

    @RequestMapping(value = "/add_exchangmanage", method = RequestMethod.POST)
    public CommonVO addExchangmanage(HongbaodianProduct hongbaodianProduct) {
        CommonVO vo = new CommonVO();
        if (StringUtils.isBlank(hongbaodianProduct.getName())) {
            vo.setSuccess(false);
            vo.setMsg("at least one param is null");
            return vo;
        }
        CommonRemoteVO remoteVO = qipaiHongBaoRemoteService.add_product(hongbaodianProduct);
        if (remoteVO.isSuccess() == false) {
            vo.setSuccess(false);
            vo.setMsg("add exchangmanage fail");
            return vo;
        }
        vo.setSuccess(true);
        vo.setMsg("add success");
        return vo;
    }

    @RequestMapping(value = "/update_exchangmanage", method = RequestMethod.POST)
    public CommonVO updateExchangmanage(HongbaodianProduct hongbaodianProduct) {
        CommonVO vo = new CommonVO();
        if (StringUtils.isBlank(hongbaodianProduct.getName())) {
            vo.setSuccess(false);
            vo.setMsg("at least one param is null");
            return vo;
        }
        CommonRemoteVO remoteVO = qipaiHongBaoRemoteService.update_product(hongbaodianProduct);
        if (remoteVO.isSuccess() == false) {
            vo.setSuccess(false);
            vo.setMsg("update exchangmanage fail ");
            return vo;
        }
        vo.setSuccess(true);
        vo.setMsg("update success");
        return vo;
    }

    @RequestMapping(value = "/delete_exchangmanage", method = RequestMethod.POST)
    public CommonVO deleteExchangmanage(String[] ids) {
        CommonVO vo = new CommonVO();
        if (ArrayUtils.isEmpty(ids)) {
            vo.setSuccess(false);
            vo.setMsg("at least one param is null");
            return vo;
        }
        CommonRemoteVO remoteVO = qipaiHongBaoRemoteService.remove_product_by_id(ids);
        if (remoteVO.isSuccess() == false) {
            vo.setSuccess(false);
            vo.setMsg("remove exchangmanage fail ");
            return vo;
        }
        vo.setSuccess(true);
        vo.setMsg("delete success");
        return vo;
    }

    @RequestMapping(value = "/get_exchangmanage", method = RequestMethod.POST)
    public CommonVO getExchangManage(String id) {
        CommonVO vo = new CommonVO();
        if (StringUtils.isBlank(id)) {
            vo.setSuccess(false);
            vo.setMsg("at least one param is null");
            return vo;
        }
        HongbaodianProduct hongbaodianProduct = exchangeManageService.getExchangeManage(id);
        vo.setSuccess(true);
        vo.setMsg("get success");
        vo.setData(hongbaodianProduct);
        return vo;
    }

    @RequestMapping(value = "/query_exchangemanage", method = RequestMethod.POST)
    public CommonVO queryExchangeManage(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "20") int size) {
        CommonVO vo = new CommonVO();
        ListPage listPage = exchangeManageService.getExchangeManages(page, size);
        vo.setSuccess(true);
        vo.setMsg("query exchangeManage success");
        vo.setData(listPage);
        return vo;
    }

    @RequestMapping(value = "/query_exchangetype", method = RequestMethod.POST)
    public CommonVO queryExchangeType() {
        CommonVO vo = new CommonVO();
        vo.setSuccess(true);
        vo.setMsg("query exchangeType success");
        vo.setData(RewardType.toMap());
        return vo;
    }

    /**
     * 白名单管理查询
     */
    @RequestMapping(value = "/query_whitelist", method = RequestMethod.POST)
    public CommonVO queryWhiteList(@RequestParam(value = "page", defaultValue = "1") int page,
                                   @RequestParam(value = "size", defaultValue = "20") int size,
                                   String playerId,
                                   String loginIp) {
        CommonVO vo = new CommonVO();
        ListPage listPage = whiteListService.findWhiteLists(page, size, playerId, loginIp);
        vo.setSuccess(true);
        vo.setMsg("query exchangeManage success");
        vo.setData(listPage);
        return vo;
    }

    @RequestMapping(value = "/add_whitelist", method = RequestMethod.POST)
    public CommonVO addWhiteList(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "20") int size,
                                 WhiteList whiteList,
                                 String token) {
        CommonVO vo = new CommonVO();
        if (StringUtils.isBlank(whiteList.getPlayerId())) {
            vo.setSuccess(false);
            vo.setMsg("at least one param is null");
            return vo;
        }
        String adminId = adminAuthService.getAdminIdBySessionId(token);
        if (adminId == null) {
            vo.setSuccess(false);
            return vo;
        }
        Admin admin = adminService.findAdminById(adminId);
        whiteList.setOperator(admin.getNickname());
        whiteListService.addWhiteList(whiteList);
        vo.setSuccess(true);
        vo.setMsg("add whitelist success");
        return vo;
    }

    @RequestMapping(value = "/delete_whitelist", method = RequestMethod.POST)
    public CommonVO deleteWhiteList(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "size", defaultValue = "20") int size,
                                    String[] ids) {
        CommonVO vo = new CommonVO();
        whiteListService.deleteWhiteList(ids);
        vo.setSuccess(true);
        vo.setMsg("delete whitelist success");
        return vo;
    }

}
