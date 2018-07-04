package com.anbang.qipai.admin.plan.domain.task;

import java.util.Map;

import com.anbang.qipai.admin.plan.domain.ClubCard;

public class TaskDocumentHistory {
	private String id;
	private String taskDocId;
	private String name;
	private String desc;
	private String type;// 任务类型
	private String taskName;// 任务种类
	private String rewardType;// 奖励类型
	private Integer gold;
	private Integer score;
	private ClubCard clubCard;
	private Map<String, String> criterions;// 完成条件
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

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public ClubCard getClubCard() {
		return clubCard;
	}

	public void setClubCard(ClubCard clubCard) {
		this.clubCard = clubCard;
	}

	public Map<String, String> getCriterions() {
		return criterions;
	}

	public void setCriterions(Map<String, String> criterions) {
		this.criterions = criterions;
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
