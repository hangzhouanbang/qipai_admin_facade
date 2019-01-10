package com.anbang.qipai.admin.plan.bean.tasks;

/**
 * @author yins
 * @Description: 兑换管理(红包点兑换)
 */
public class ExchangeManage {
    private String id;
    private String type;    //红包点兑换
    private String exchangeType;
    private Integer exchangeCount;
    private String itemName;
    private String exchangeConsumption; //兑换消耗

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Integer getExchangeCount() {
        return exchangeCount;
    }

    public void setExchangeCount(Integer exchangeCount) {
        this.exchangeCount = exchangeCount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getExchangeConsumption() {
        return exchangeConsumption;
    }

    public void setExchangeConsumption(String exchangeConsumption) {
        this.exchangeConsumption = exchangeConsumption;
    }
}
