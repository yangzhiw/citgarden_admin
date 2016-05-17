package com.citygarden.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzw on 2016/5/17 0017.
 */

@Document(collection = "T_PROVIDE_MERCHANT")
public class ProvideMerchant extends AbstractAuditingEntity {
    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    @Field("provide_dishs")
    private List<ProvideDish> provideDishs = new ArrayList<>(0);

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

    public List<ProvideDish> getProvideDishs() {
        return provideDishs;
    }

    public void setProvideDishs(List<ProvideDish> provideDishs) {
        this.provideDishs = provideDishs;
    }

    @Override
    public String toString() {
        return "ProvideMerchant{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", provideDishs=" + provideDishs +
            '}';
    }
}
