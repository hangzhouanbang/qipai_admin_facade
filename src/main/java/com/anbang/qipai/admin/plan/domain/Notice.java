package com.anbang.qipai.admin.plan.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Notice")
public class Notice {
	
	@Id
	private String id;//公告ID
	private Integer state;//状态{1代表启用，0代表禁用}
	private String notice;//公告信息
	public Notice() {
		
	}
	
	public Notice(String id,Integer state,String notice) {
		this.id=id;
		this.state=state;
		this.notice=notice;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
}
