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
	public List<Admin> queryAllByPage(int page, int rows) {
		PageRequest pageRequest = new PageRequest(page - 1, rows);
		return adminRepository.findAll(pageRequest).getContent();
	}

	@Override
	public List<Admin> queryByNameAndPage(int page, int rows, String nickName) {
		PageRequest pageRequest = new PageRequest(page - 1, rows);
		return adminRepository.findByNickNameLike(nickName, pageRequest).getContent();
	}

	@Override
	public Admin addAdmin(Admin admin) {
		return adminRepository.insert(admin);
	}

	@Override
	public void deleteAdmins(String[] ids) {
		for (String id : ids) {
			adminRepository.delete(id);
		}
	}

	@Override
	public Admin editAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

}
