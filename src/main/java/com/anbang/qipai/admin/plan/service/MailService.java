package com.anbang.qipai.admin.plan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.systemmaildao.MailDao;
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
			 criteria.andOperator(Criteria.where("adminname").is(adminname),Criteria.where("status").is(status));//多条件查询
			 query = new Query(criteria);
		 }
		return maildao.findall(query,pageable,size);
	}

	public void addmail(SystemMail mail) {
		maildao.addmail(mail);
	}

	public void deletemail(Integer id) {
		
	}

}
