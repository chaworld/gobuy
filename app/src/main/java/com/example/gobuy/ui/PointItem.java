package com.example.gobuy.ui;
//一筆資料的結構
public class PointItem {
    private String storeName;       // 店家名稱
    private String transactionDate; // 交易日期
    private String transactionTime; // 時間
    private int pointsChange;       // 紅利點數變化 (+650, -100 等)

    // 建構子
    public PointItem(String storeName, String transactionDate, String transactionTime, int pointsChange) {
        this.storeName = storeName;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.pointsChange = pointsChange;
    }

    // Getter 全部的方法
    public String getStoreName() {
        return storeName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public int getPointsChange() {
        return pointsChange;
    }
}