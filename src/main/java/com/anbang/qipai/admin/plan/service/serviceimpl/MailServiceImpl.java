package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.systemmaildao.MailDao;
import com.anbang.qipai.admin.plan.domain.mail.SystemMail;
import com.anbang.qipai.admin.plan.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private MailDao maildao;
	

	@Override
	public Map<String,Object> findall(Integer page,Integer size) {
		 if(page < 1) {
			 page = 1;
		 }
		 Sort sort = new Sort(Sort.Direction.DESC, "createtime");
		 
		 Pageable pageable= new PageRequest(page-1, size,sort);
		 
		 Query query = new Query();
		 
		return maildao.findall(query,pageable,size);
	}

	@Override
	public void addmail(SystemMail mail) {
		maildao.addmail(mail);
	}

	@Override
	public void deletemail(Integer id) {
		
	}

}
