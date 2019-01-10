package com.anbang.qipai.admin.plan.service.tasksservice;

import com.anbang.qipai.admin.constant.Constants;
import com.anbang.qipai.admin.plan.bean.tasks.ExchangeManage;
import com.anbang.qipai.admin.plan.dao.tasksdao.ExchangeManageDao;
import com.highto.framework.web.page.ListPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yins
 * @Description: TODO
 */
@Service
public class ExchangeManageService {

    @Autowired
    private ExchangeManageDao exchangeManageDao;

    public void addExchangeManage(ExchangeManage exchangeManage) {
        exchangeManage.setType(Constants.Task.EXCHANGE_TYPE);
        exchangeManageDao.addExchangeManage(exchangeManage);
    }

    public void deleteExchangeManage(String id) {
        exchangeManageDao.deleteExchangeManage(id);
    }

    public void updateExchangeManage(ExchangeManage exchangeManage) {
        exchangeManageDao.updateExchangeManage(exchangeManage);
    }

    public ExchangeManage getExchangeManage(String id) {
        return exchangeManageDao.getExchangeManage(id);
    }

    public ListPage getExchangeManages(int page, int size) {
        long count = exchangeManageDao.countExchangeMange();
        List<ExchangeManage> exchangeManages = exchangeManageDao.getExchangeManages(page, size);
        ListPage result = new ListPage(exchangeManages, page, size, (int) count);
        return result;
    }
}
