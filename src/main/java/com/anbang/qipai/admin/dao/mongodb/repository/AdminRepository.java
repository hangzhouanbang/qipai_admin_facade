package com.anbang.qipai.admin.dao.mongodb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.anbang.qipai.admin.entity.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {

	/**根据管理员姓名进行分页查询
	 * @param name	要查询的管理员姓名
	 * @param pageable	分页信息
	 * @return	结果集
	 */
	Page<Admin> findByNameLike(String name,Pageable pageable);
	
	Admin findById(String id);
}
