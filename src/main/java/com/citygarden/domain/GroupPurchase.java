package com.citygarden.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GroupPurchase.
 */

@Document(collection = "group_purchase")
public class GroupPurchase implements Serializable {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupPurchase groupPurchase = (GroupPurchase) o;
        if(groupPurchase.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, groupPurchase.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "GroupPurchase{" +
            "id=" + id +
            '}';
    }
}
