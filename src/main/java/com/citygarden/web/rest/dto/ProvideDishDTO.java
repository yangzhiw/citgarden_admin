package com.citygarden.web.rest.dto;

import com.citygarden.domain.ProvideDish;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class ProvideDishDTO {
    private String id;
    private String name;
    private double price;
    private String chineseName;
    private String provideDishPhoto;

    private String provideMerchantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProvideDishPhoto() {
        return provideDishPhoto;
    }

    public void setProvideDishPhoto(String provideDishPhoto) {
        this.provideDishPhoto = provideDishPhoto;
    }

    public String getProvideMerchantId() {
        return provideMerchantId;
    }

    public void setProvideMerchantId(String provideMerchantId) {
        this.provideMerchantId = provideMerchantId;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @Override
    public String toString() {
        return "ProvideDishDTO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", chineseName='" + chineseName + '\'' +
            ", provideDishPhoto='" + provideDishPhoto + '\'' +
            ", provideMerchantId='" + provideMerchantId + '\'' +
            '}';
    }
}
