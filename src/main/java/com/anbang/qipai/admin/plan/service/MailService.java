package com.anbang.qipai.admin.plan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.systemmaildao.MailDao;
import com.anbang.qipai.admin.plan.domain.mail.MailList;
import com.anbang.qipai.admin.plan.domain.mail.MailState;
import com.anbang.qipai.admin.plan.domain.mail.SystemMail;

@Service
public class MailService{
	
	@Autowired
	private MailDao maildao;

	public Map<String,Object> findall(Integer page,Integer size,String adminname,Integer status) {
		 if(page < 1) {
			 page = 1;
		 }
		 Sort sort = new Sort(Sort.Direction.DESC, "createtime");
		 
		 Pageable pageable= new PageRequest(page-1, size,sort);
		 Query query = null;
		 if(adminname == null || adminname.equals("")) {
			 query = new Query(Criteria.where("status").is(status));
		 }else {
			 Criteria criteria = new Criteria();
			 criteria.andOperator(Criteria.where("adminname").regex(adminname),Criteria.where("status").is(status));//多条件查询
			 query = new Query(criteria);
		 }
		return maildao.findall(query,pageable,size);
	}
	
	public Map<String,Object> find_mail_record(Integer page,Integer size,String memberId,String mailType,String adminName,long startTime,long endTime){
		if(page < 1) {
			 page = 1;
		 }
		Map<String,Object> map = new HashMap<String, Object>();
		List<MailList> lists = new ArrayList<MailList>();
		Query query = new Query();
		Long count = maildao.count(query);//总条数
		Long pageCount = count%size==0?count/size:count/size+1;//总页数
		Pageable pageable = new PageRequest(page-1,size);
		if(memberId != null && !memberId.equals("")) {
			 query.addCriteria(Criteria.where("memberid").is(memberId));
		}
		if(mailType != null && !mailType.equals("")) {
			query.addCriteria(Criteria.where("mailType").is(mailType));
		}
		if(adminName != null && !"".equals(adminName)) {
			query.addCriteria(Criteria.where("adminname").regex(adminName));
		}
		if(startTime != 0 && endTime != 0) {
			query.addCriteria(Criteria.where("createtime").gte(startTime));
			query.addCriteria(Criteria.where("createtime").lte(endTime));
		}
		List<MailState> list = maildao.find_mail_record(query,pageable);
		for(MailState mailState:list) {
			SystemMail systemMail = maildao.findMailById(mailState.getMailid());
			MailList mailList = new MailList();
			mailList.setReceive(mailState.getReceive());
			//mailList.setRewardTime(mailState);
			mailList.setMemberId(mailState.getMemberid());
			mailList.setSystemMail(systemMail);
			lists.add(mailList);
		}
		map.put("lists",lists);
		map.put("pageCount",pageCount);
		return map;
	}

	public void addmail(SystemMail mail) {
		maildao.addmail(mail);
	}
	
	public void updateMailState(MailState mailState) {
		maildao.updateMailState(mailState);
	}
	
	/**把一个用户的所有邮件设为已读
	 * **/
	public void findallmembermail(String memberid) {
		List<MailState> list = maildao.findallmembermail(memberid);
		for (MailState mailState : list) {//循环把这个会员的所有邮件设为已读
			mailState.setStatemail("0");
			maildao.updateMailState(mailState);
		}
	}
	
	public void addMailById(List<MailState> list) {
		for(MailState mailState:list) {
			maildao.addMailById(mailState);
		}
	}

	/**删除所有已读
	 * @param memberid 会员id
	 * **/
	public void deleteallmail(String memberid) {
		List<MailState> list = maildao.findallmembermail(memberid);
		for (MailState mailState : list) {
			if(mailState.getStatemail().equals("0") && mailState.getReceive().equals("0") || mailState.getReceive().equals("2")) {
				mailState.setDeletestate("0");
			}
			maildao.updateMailState(mailState);
		}
	}

}
