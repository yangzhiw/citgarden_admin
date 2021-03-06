package com.citygarden.web.rest.dto;

import com.citygarden.domain.OrderItem;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4 0004.
 */
public class OrderDTO {
    @Id
    private String id;

    @Field("total_price")
    private double totalPrice;
    @Field("delivery_way")
    private String deliveryWay;
    private DateTime date = new DateTime();
    @Field("order_status")
    private String orderStatus;
    private String deliveryAddress;

    private List<OrderItem> orderItemList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryWay() {
        return deliveryWay;
    }

    public void setDeliveryWay(String deliveryWay) {
        this.deliveryWay = deliveryWay;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
            "id='" + id + '\'' +
            ", totalPrice=" + totalPrice +
            ", deliveryWay='" + deliveryWay + '\'' +
            ", date=" + date +
            ", orderStatus='" + orderStatus + '\'' +
            ", deliveryAddress='" + deliveryAddress + '\'' +
            ", orderItemList=" + orderItemList +
            '}';
    }
}
