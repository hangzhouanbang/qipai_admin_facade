package com.anbang.qipai.admin.plan.service.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.plan.dao.AccountDao;
import com.anbang.qipai.admin.plan.domain.Account;
import com.anbang.qipai.admin.plan.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, Account account) {
		Map<String, Object> map = new HashMap<String, Object>();
		long amount = accountDao.getAmount(new Query());
		long pageNumber = (amount == 0) ? 1 : ((amount % size == 0) ? (amount / size) : (amount / size + 1));
		Map<String, Object> paramMap = null;
		if (account.getPayerId() != null) {
			paramMap = new HashMap<String, Object>();
			paramMap.put("payerId", account.getPayerId());
		}
		List<Account> accountList = accountDao.queryByConditionsAndPage(paramMap, (page - 1) * size, size, sort);
		map.put("pageNumber", pageNumber);
		map.put("accountList", accountList);
		return map;
	}

}
