package com.anbang.qipai.admin.plan.dao.systemmaildao;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import com.anbang.qipai.admin.plan.domain.mail.SystemMail;


/**设置邮件service
 * @author 程佳 2018.6.6
 * **/
public interface MailDao {
	
	/**查询所有邮件
	 * @return 所有邮件信息
	 * **/
	Map<String,Object> findall(Query query,Pageable pageable,Integer size);
	
	/**添加邮件
	 * @param mail 要添加的邮件对象
	 * **/
	void addmail(SystemMail mail);
	
	/**删除邮件
	 * @param id 要删除的邮件id
	 * **/
	void deletemail(Integer id);
	
	

}
