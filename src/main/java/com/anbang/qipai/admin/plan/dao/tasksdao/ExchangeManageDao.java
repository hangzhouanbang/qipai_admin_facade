package com.anbang.qipai.admin.plan.dao.tasksdao;

import com.anbang.qipai.admin.plan.bean.tasks.ExchangeManage;

import java.util.List;

public interface ExchangeManageDao {

    void addExchangeManage(ExchangeManage exchangeManage);

    void updateExchangeManage(ExchangeManage exchangeManage);

    void deleteExchangeManage(String id);

    ExchangeManage getExchangeManage(String id);

    long countExchangeMange();

    List<ExchangeManage> getExchangeManages(int page, int size);
}
