package com.tabwu.health.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约设置
 */
public class OrderSetting implements Serializable{
    private Integer id ;
    private String orderDate;//预约设置日期
    private int number;//可预约人数
    private int reservations ;//已预约人数

    public OrderSetting(Object o, String s, int i, Object o1) {
    }

    public OrderSetting(String orderDate, int number) {
        this.orderDate = orderDate;
        this.number = number;
    }

    public OrderSetting(String orderDate, String s) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "OrderSetting{" +
                "id=" + id +
                ", orderDate='" + orderDate + '\'' +
                ", number=" + number +
                ", reservations=" + reservations +
                '}';
    }
}
