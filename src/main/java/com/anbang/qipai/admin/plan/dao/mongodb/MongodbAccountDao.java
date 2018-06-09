package com.anbang.qipai.admin.plan.dao.mongodb;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.plan.dao.AccountDao;
import com.anbang.qipai.admin.plan.domain.Account;

@Component
public class MongodbAccountDao implements AccountDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public long getAmount(Query query) {
		return mongoTemplate.count(query, Account.class);
	}

	@Override
	public List<Account> queryByConditionsAndPage(Map<String, Object> paramMap, int start, int size, Sort sort) {
		Query queryBySearch = getQueryBySearch(paramMap, true);
		queryBySearch.skip(start);
		queryBySearch.limit(size);
		queryBySearch.with(sort);
		return mongoTemplate.find(queryBySearch, Account.class);
	}

	private Query getQueryBySearch(Map<String, Object> paramMap, boolean stringRegex) {
		Query query = new Query();
		if (paramMap == null) {
			return query;
		}

		Criteria criteria = new Criteria();
		for (Entry<String, Object> kv : paramMap.entrySet()) {
			String key = kv.getKey();

			if (key != null) {
				Object value = kv.getValue();
				if (value != null) {
					if (stringRegex && value instanceof String) {
						String v = value.toString();
						while (v.endsWith("\\")) {
							v = v.substring(0, v.length() + 1 - 2);
						}
						criteria.and(key).regex(v);
					} else {
						criteria.and(key).is(value);
					}
				}
			}
		}

		query.addCriteria(criteria);
		return query;
	}

}
