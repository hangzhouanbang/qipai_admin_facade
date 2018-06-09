package com.anbang.qipai.admin.plan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import com.anbang.qipai.admin.plan.domain.Account;

public interface AccountDao {
	long getAmount(Query query);

	List<Account> queryByConditionsAndPage(Map<String, Object> paramMap, int start, int size, Sort sort);
}
