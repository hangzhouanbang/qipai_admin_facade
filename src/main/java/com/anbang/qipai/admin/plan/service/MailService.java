package com.anbang.qipai.admin.plan.service;
/**设置邮件service
 * @author 程佳 2018.6.6
 * **/

import java.util.Map;

import com.anbang.qipai.admin.plan.domain.mail.SystemMail;

public interface MailService {
	/**查询所有邮件
	 * @return 所有邮件信息
	 * **/
	Map<String,Object> findall(Integer page,Integer size);
	
	/**添加邮件
	 * @param mail 要添加的邮件对象
	 * **/
	void addmail(SystemMail mail);

	/**删除邮件
	 * @param id 要删除的邮件id
	 * **/
	void deletemail(Integer id);

}
