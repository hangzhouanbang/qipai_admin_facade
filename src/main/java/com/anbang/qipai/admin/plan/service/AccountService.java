package com.anbang.qipai.admin.plan.service;

import java.util.Map;

import org.springframework.data.domain.Sort;

import com.anbang.qipai.admin.plan.domain.Account;

public interface AccountService {
	Map<String, Object> queryByConditionsAndPage(int page, int size, Sort sort, Account account);
}
