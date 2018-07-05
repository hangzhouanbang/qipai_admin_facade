package com.anbang.qipai.admin.plan.domain.task;

public class TaskDocumentHistory {
	private String id;
	private String taskDocId;
	private String name;
	private String desc;
	private String type;// 任务类型
	private String taskName;// 任务种类
	private String rewardType;// 奖励类型
	private Integer rewardNum;// 奖励数量
	private String vip;
	private Integer targetNum;// 完成次数
	private Integer state;// 状态:0,未发布,1,已发布
	private Long releaseTime;
	private String promulgator;// 发布者

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskDocId() {
		return taskDocId;
	}

	public void setTaskDocId(String taskDocId) {
		this.taskDocId = taskDocId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

	public Integer getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(Integer rewardNum) {
		this.rewardNum = rewardNum;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public Integer getTargetNum() {
		return targetNum;
	}

	public void setTargetNum(Integer targetNum) {
		this.targetNum = targetNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Long releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getPromulgator() {
		return promulgator;
	}

	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}

}
