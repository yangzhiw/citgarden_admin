package com.citygarden.web.rest.dto;

import com.citygarden.domain.Dish;

/**
 * Created by yzw on 2016/5/25 0025.
 */
public class ProfitReportsDTO {
    private String id;

    private String dishId;

    private Dish dish;

    private double orginalPrice;

    private double salePrice;

    private int saleCount;

    private double saleTotalPrice;

    private double inputTotalPrice;

    private double totalProfit;

    private int destroyCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public double getOrginalPrice() {
        return orginalPrice;
    }

    public void setOrginalPrice(double orginalPrice) {
        this.orginalPrice = orginalPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public double getSaleTotalPrice() {
        return saleTotalPrice;
    }

    public void setSaleTotalPrice(double saleTotalPrice) {
        this.saleTotalPrice = saleTotalPrice;
    }

    public double getInputTotalPrice() {
        return inputTotalPrice;
    }

    public void setInputTotalPrice(double inputTotalPrice) {
        this.inputTotalPrice = inputTotalPrice;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getDestroyCount() {
        return destroyCount;
    }

    public void setDestroyCount(int destroyCount) {
        this.destroyCount = destroyCount;
    }

    @Override
    public String toString() {
        return "ProfitReportsDTO{" +
            "id='" + id + '\'' +
            ", dishId='" + dishId + '\'' +
            ", dish=" + dish +
            ", orginalPrice=" + orginalPrice +
            ", salePrice=" + salePrice +
            ", saleCount=" + saleCount +
            ", saleTotalPrice=" + saleTotalPrice +
            ", inputTotalPrice=" + inputTotalPrice +
            ", totalProfit=" + totalProfit +
            ", destroyCount=" + destroyCount +
            '}';
    }
}
