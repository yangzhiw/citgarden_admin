package com.citygarden.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A RepertoryManager.
 */

@Document(collection = "T_REPERTORY_MANAGER")
public class RePertoryManager extends AbstractAuditingEntity {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("now_count")
    private Integer nowCount;

    @Field("total_count")
    private Long totalCount;

    @DBRef(lazy = true)
    @Field("dish")
    private Dish dish;

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

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

    public Integer getNowCount() {
        return nowCount;
    }

    public void setNowCount(Integer nowCount) {
        this.nowCount = nowCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "RepertoryManager{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", nowCount=" + nowCount +
            ", totalCount=" + totalCount +
            ", dish=" + dish +
            '}';
    }
}
