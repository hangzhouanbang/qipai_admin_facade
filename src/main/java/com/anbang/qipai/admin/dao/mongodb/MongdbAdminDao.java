package com.anbang.qipai.admin.dao.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.anbang.qipai.admin.dao.AdminDao;
import com.anbang.qipai.admin.dao.mongodb.repository.AdminRepository;
import com.anbang.qipai.admin.entity.Admin;
@Component
public class MongdbAdminDao implements AdminDao {
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public List<Admin> queryByNameAndPage(int page, int rows, String name) {
		PageRequest pageRequest = new PageRequest(page - 1, rows);
		return adminRepository.findByNameLike(name, pageRequest).getContent();
	}

}
