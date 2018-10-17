package com.anbang.qipai.admin.plan.service.signinservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.qipai.admin.msg.service.SignInPrizeSourceService;
import com.anbang.qipai.admin.plan.bean.signin.SignInPrize;
import com.anbang.qipai.admin.plan.dao.signindao.SignInPrizeDao;

@Service
public class SignInPrizeService {

	@Autowired
	private SignInPrizeDao signInPrizeDao;
	
	@Autowired
	private SignInPrizeSourceService signInPrizeSourceService;
	
	/**
	 * 添加签到奖品
	 * @param signInPrize
	 */
	public void addSignInPrize(SignInPrize signInPrize) {
		signInPrize.setState("1");
		signInPrizeDao.addSignInPrize(signInPrize);
	}

	/**
	 * 修改签到奖品
	 * @param signInPrize
	 */
	public void updateSignInPrize(SignInPrize signInPrize) {
		signInPrizeDao.updateSignInPrize(signInPrize);
	}

	/**
	 * 查询所有签到奖品
	 * @return
	 */
	public List<SignInPrize> querySignInPrize() {
		return signInPrizeDao.querySignInPrize();
	}

	/**
	 * 根据id查询签到奖品
	 * @param id
	 * @return
	 */
	public SignInPrize querySignInPrizeById(String id) {
		return signInPrizeDao.querySignInPrizeById(id);
	}
	
	/**
	 * 根据id删除签到奖品
	 * @param id
	 */
	public void deleteSignInPrizeById(String id) {
		signInPrizeDao.deleteSignInPrizeById(id);
	}

	public int countSignInPrize() {
		return (int) signInPrizeDao.countSignInPrize();
	}

	public void releaseSignInPrize() {
		signInPrizeSourceService.releaseSignInPrize();
		signInPrizeDao.releaseSignInPrize();
	}

	public void decreaseStoreById(String id) {
		signInPrizeDao.decreaseStoreById(id);
	}

	
}
