package com.citygarden.web.rest.dto;

import com.citygarden.domain.Dish;
import com.citygarden.domain.ProvideDish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public class provideMerchantDTO {
    private String id;

    private String name;

    private String chineseName;

    private String description;

    private List<DishDTO> dishs = new ArrayList<>();

    private List<ProvideDishDTO> provideDishs = new ArrayList<>(0);

    private String dishPhoto;

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

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DishDTO> getDishs() {
        return dishs;
    }

    public void setDishs(List<DishDTO> dishs) {
        this.dishs = dishs;
    }

    public List<ProvideDishDTO> getProvideDishs() {
        return provideDishs;
    }

    public void setProvideDishs(List<ProvideDishDTO> provideDishs) {
        this.provideDishs = provideDishs;
    }

    public String getDishPhoto() {
        return dishPhoto;
    }

    public void setDishPhoto(String dishPhoto) {
        this.dishPhoto = dishPhoto;
    }

    @Override
    public String toString() {
        return "provideMerchantDTO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", chineseName='" + chineseName + '\'' +
            ", description='" + description + '\'' +
            ", dishs=" + dishs +
            ", provideDishs=" + provideDishs +
            ", dishPhoto='" + dishPhoto + '\'' +
            '}';
    }
}
