package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.systemmaildao.MailDao;
import com.anbang.qipai.admin.plan.domain.mail.SystemMail;


@Component
public class MongodbMailDao implements MailDao{

	@Autowired
	private MongoTemplate mongotemplate;
	
	@Override
	public Map<String,Object> findall(Query query,Pageable pageable,Integer size) {
		 Map<String,Object> map = new HashMap<String,Object>();
		 Long total = mongotemplate.count(query, SystemMail.class);
		 Long count = total%size==0?total/size:total/size+1;
		 List<SystemMail> list = mongotemplate.find(query.with(pageable),SystemMail.class);
		 map.put("list", list);
		 map.put("count",count);
		 System.out.println("总页数："+count);
		 System.out.println("list大小："+list.size());
		return map;
	}

	@Override
	public void addmail(SystemMail mail) {
		mongotemplate.insert(mail);
	}

	@Override
	public void deletemail(Integer id) {
		
	}

	
}
