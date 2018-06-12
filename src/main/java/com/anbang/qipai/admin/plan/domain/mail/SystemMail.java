package com.anbang.qipai.admin.plan.domain.mail;


public class SystemMail {
	private String id;//邮件id
	
	private String title;//邮件标题
	
	private String adminid;//管理员id
	
	private String file;//图片文档
	
	private long createtime;//发布时间
	
	private Integer number;//金币数量
	
	private Integer integral;//积分数量
	
	private Integer vipcard;//会员体验时间，按日来计算

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getVipcard() {
		return vipcard;
	}

	public void setVipcard(Integer vipcard) {
		this.vipcard = vipcard;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	

	
	
	
	

}
