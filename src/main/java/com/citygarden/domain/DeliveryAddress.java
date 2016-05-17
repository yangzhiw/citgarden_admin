package com.citygarden.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by yzw on 2016/5/16 0016.
 */

@Document(collection = "T_DELIVERY_ADDRESS")
public class DeliveryAddress extends AbstractAuditingEntity{
    @Id
    private String id;

    private String address;
    @Field("is_default")
    private String isDefault;

    private String  username;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DeliveryAddress{" +
            "id='" + id + '\'' +
            ", address='" + address + '\'' +
            ", isDefault='" + isDefault + '\'' +
            ", username='" + username + '\'' +
            '}';
    }
}
