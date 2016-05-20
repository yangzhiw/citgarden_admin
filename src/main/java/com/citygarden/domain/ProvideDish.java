package com.citygarden.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by Administrator on 2016/5/17 0017.
 */
@Document(collection = "T_PROVIDE_DISH")
public class ProvideDish extends AbstractAuditingEntity {
    @Id
    private String id;

    private String name;

    private double price;

    private String provideId;

    private String ChineseName;

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

    public String getProvideId() {
        return provideId;
    }

    public void setProvideId(String provideId) {
        this.provideId = provideId;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        ChineseName = chineseName;
    }

    @Override
    public String toString() {
        return "ProvideDish{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", provideId='" + provideId + '\'' +
            ", ChineseName='" + ChineseName + '\'' +
            '}';
    }
}
