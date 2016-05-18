package com.citygarden.service;

import com.citygarden.domain.Dish;
import com.citygarden.domain.ProvideDish;
import com.citygarden.domain.ProvideMerchant;
import com.citygarden.repository.ProvideMerchantRepository;
import com.citygarden.web.rest.dto.DishDTO;
import com.citygarden.web.rest.dto.ProvideDishDTO;
import com.citygarden.web.rest.dto.ProvideMerchantDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
@Service
public class ProvideMerchantService {

    @Inject
    private ProvideMerchantRepository provideMerchantRepository;

    @Inject
    private ProvideMerchantPhotoService provideMerchantPhotoService;

    @Inject
    private DishPhotoUtilService dishPhotoUtilService;


    public ProvideMerchantDTO findOne(String id) throws Exception{
        ProvideMerchant provideMerchant = provideMerchantRepository.findOne(id);
        ProvideMerchantDTO provideMerchantDTO = new ProvideMerchantDTO();
        provideMerchantDTO.setId(provideMerchant.getId());
        provideMerchantDTO.setName(provideMerchant.getName());
        provideMerchantDTO.setChineseName(provideMerchant.getChineseName());
        provideMerchantDTO.setDescription(provideMerchant.getDescription());
        List<ProvideDish> provideDishs = provideMerchant.getProvideDishs();
        List<ProvideDishDTO> provideDishDTOs = new ArrayList<>();
        List<DishDTO> dishDTOs = new ArrayList<>();
        provideDishs.forEach(x->{
            ProvideDishDTO y = new ProvideDishDTO();
            y.setId(x.getId());
            y.setName(x.getName());
            y.setPrice((x.getPrice()));
            try {
                y.setProvideDishPhoto(dishPhotoUtilService.getDishPhoto(x.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            provideDishDTOs.add(y);

        });
        List<Dish> saleDishs = provideMerchant.getDishs();
        saleDishs.forEach(x->{
            DishDTO y = new DishDTO();
            y.setId(x.getId());
            y.setName(x.getName());
            y.setChineseName(x.getChineseName());
            y.setOriginalPrice(x.getOriginalPrice());
            y.setDiscountPrice(x.getDiscountPrice());
            try {
                y.setDishPhoto(dishPhotoUtilService.getDishPhoto(x.getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            dishDTOs.add(y);
        });

        provideMerchantDTO.setDishs(dishDTOs);
        provideMerchantDTO.setProvideDishs(provideDishDTOs);
        provideMerchantDTO.setProvidePhoto(provideMerchantPhotoService.getProvidePhoto(provideMerchant.getName()));

        return  provideMerchantDTO;
    }
}
