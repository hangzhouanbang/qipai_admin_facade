package com.anbang.qipai.admin.plan.dao.daobase.mongodb;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.anbang.qipai.admin.plan.dao.CMemberDao;
import com.anbang.qipai.admin.plan.dao.daobase.MongodbDaoBase;
import com.anbang.qipai.admin.plan.domain.Member;

public class MongodbMemberDao extends MongodbDaoBase implements CMemberDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Member> queryByConditionsAndPage(int page, int size, Member member) {
		Map<String, Object> paramMap = null;
		if (member != null) {
			paramMap = new HashMap<String, Object>();
			Field[] fields = Member.class.getDeclaredFields();
			for (Field field : fields) {
				String methodName = "get" + field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1);
				Method method;
				try {
					method = Member.class.getMethod(methodName, new Class<?>[0]);
					paramMap.put(field.getName(), method.invoke(member));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Sort sort = new Sort(new Order("id"));
		super.setMongoTemplate(mongoTemplate);
		return searchLike(paramMap, (page - 1) * size, size, sort, Member.class);
	}

}
