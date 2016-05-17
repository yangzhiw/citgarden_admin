package com.citygarden.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by yzw on 2016/5/16 0016.
 */
@Document(collection = "T_USER_LEVEL_DEFINITION")
public class UserLevalDefinition {

    @Id
    private String id;

    @Field("gold_integral")
    private int goldIntegral;

    @Field("platina_integral")
    private int platinaIntegral;

    @Field("diamond_integral")
    private int diamondIntegral;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGoldIntegral() {
        return goldIntegral;
    }

    public void setGoldIntegral(int goldIntegral) {
        this.goldIntegral = goldIntegral;
    }

    public int getPlatinaIntegral() {
        return platinaIntegral;
    }

    public void setPlatinaIntegral(int platinaIntegral) {
        this.platinaIntegral = platinaIntegral;
    }

    public int getDiamondIntegral() {
        return diamondIntegral;
    }

    public void setDiamondIntegral(int diamondIntegral) {
        this.diamondIntegral = diamondIntegral;
    }

    @Override
    public String toString() {
        return "UserLevalDefinition{" +
            "id='" + id + '\'' +
            ", goldIntegral=" + goldIntegral +
            ", platinaIntegral=" + platinaIntegral +
            ", diamondIntegral=" + diamondIntegral +
            '}';
    }
}
