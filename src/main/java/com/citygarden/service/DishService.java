package com.citygarden.service;

import com.citygarden.domain.*;
import com.citygarden.domain.util.CloudxEnums;
import com.citygarden.repository.*;
import com.citygarden.web.rest.dto.DishDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/24 0024.
 */

@Service
public class DishService {

    private final Logger log = LoggerFactory.getLogger(DishService.class);

    @Inject
    private DishRepository dishRepository;

    @Inject
    private DishPhotoUtilService dishPhotoUtilService;

    @Inject
    private ProvideMerchantRepository provideMerchantRepository;

    @Inject
    private RePertoryManagerRepository rePertoryManagerRepository;

    @Inject
    private DishRelationProvideRepository dishRelationProvideRepository;

    @Inject
    private ProvideDishRepository provideDishRepository;

    /**
     * 查询有序菜品
     * @return
     * @throws Exception
     */
    public List<DishDTO> findAll() throws  Exception{
        List<Dish> dishs = dishRepository.findAll();
        List<DishDTO> dishDTOs = new ArrayList<>();
        DishDTO dishDTO;
        for(Dish dish : dishs){
            dishDTO = new DishDTO();

            dishDTO.setId(dish.getId());
            dishDTO.setName(dish.getName());
            dishDTO.setDiscountPrice(dish.getDiscountPrice());
            dishDTO.setIsDiscount(dish.getIsDiscount());
            dishDTO.setIsGain(dish.getIsGain());
            dishDTO.setIsHot(dish.getIsHot());
            dishDTO.setDescription(dish.getDescription());
            dishDTO.setOriginalPrice(dish.getOriginalPrice());
            dishDTO.setChineseName(dish.getChineseName());
            dishDTO.setDishPhoto(dishPhotoUtilService.getDishPhoto(dish.getName()));

            String provideMerchantId = dishRelationProvideRepository.findByDishId(dish.getId())!= null
                ? dishRelationProvideRepository.findByDishId(dish.getId()).getProvideMerchantId():null;

            if(provideMerchantId !=  null){
                dishDTO.setProvideMerchantName(provideMerchantRepository.findOne(provideMerchantId) != null ?
                    provideMerchantRepository.findOne(provideMerchantId).getName() : null);
            }

            dishDTO.setNowCount(String.valueOf(
                rePertoryManagerRepository.findByDishIdAndProvideId(dish.getId(), provideMerchantId) != null
                    ? rePertoryManagerRepository.findByDishIdAndProvideId(dish.getId(),provideMerchantId).getNowCount() : null));
            System.err.println(dishDTO);

            dishDTOs.add(dishDTO);
        }
        return dishDTOs;
    }

    public DishDTO findOne(String id)  throws Exception{
        Dish dish =  dishRepository.findOne(id);
        DishDTO dishDTO = new DishDTO();

        dishDTO.setId(dish.getId());
        dishDTO.setName(dish.getName());
        dishDTO.setDiscountPrice(dish.getDiscountPrice());
        dishDTO.setIsDiscount(dish.getIsDiscount());
        dishDTO.setIsGain(dish.getIsGain());
        dishDTO.setIsHot(dish.getIsHot());
        dishDTO.setOriginalPrice(dish.getOriginalPrice());
        dishDTO.setChineseName(dish.getChineseName());
        dishDTO.setDescription(dish.getDescription());

        String provideMerchantId = dishRelationProvideRepository.findByDishId(dish.getId())!= null
            ? dishRelationProvideRepository.findByDishId(dish.getId()).getProvideMerchantId():null;

        if(provideMerchantId !=  null){
            dishDTO.setProvideMerchantName(provideMerchantRepository.findOne(provideMerchantId).getName());
        }

        dishDTO.setNowCount(String.valueOf(
            rePertoryManagerRepository.findByDishIdAndProvideId(dish.getId(), provideMerchantId) != null
                ? rePertoryManagerRepository.findByDishIdAndProvideId(dish.getId(), provideMerchantId).getNowCount() : null));
        dishDTO.setDishPhoto(dishPhotoUtilService.getDishPhoto(dish.getName()));

        return dishDTO;

    }

    /**
     * 保存菜品
     * @param dishDto
     * @return
     */
    public Dish save(DishDTO dishDto) {
        System.err.println(dishDto);
        Dish dish = new Dish();
        dish.setName(dishDto.getName());
        dish.setOriginalPrice(dishDto.getOriginalPrice());
        dish.setDiscountPrice(dishDto.getDiscountPrice());
        dish.setChineseName(dishDto.getChineseName());
        dish.setDescription(dishDto.getDescription());
        if(StringUtils.isBlank(dishDto.getIsHot())){
            dish.setIsHot(CloudxEnums.HotEnum.ISHOT);
        }else{
            dish.setIsHot(dishDto.getIsHot());
        }
        if((Double.valueOf(dishDto.getOriginalPrice()) - Double.valueOf(dishDto.getDiscountPrice())) > 0 ){
            dish.setIsDiscount(CloudxEnums.DicountEnum.ISDISCOUNT);
        }else{
            dish.setIsDiscount(CloudxEnums.DicountEnum.UNDISCOUNT);
        }

        ProvideMerchant provideMerchant = provideMerchantRepository.findOne(dishDto.getProvideMerchantId());
        String provideName = provideMerchant.getName();

//        RePertoryManager rePertoryManager = rePertoryManagerRepository.findByDishNameAndProvideName(dishDto.getName(), provideName);
//        if(rePertoryManager != null){
//            int nowCount = rePertoryManager.getNowCount();
//            if(nowCount > 0){
//                dish.setIsGain(CloudxEnums.GainEnum.ISGAIN);
//            }else{
//                dish.setIsGain(CloudxEnums.GainEnum.UNGAIN);
//            }
//        }
        dish.setIsGain(CloudxEnums.GainEnum.ISGAIN);
        Dish result = dishRepository.save(dish);
        provideMerchant.getDishs().add(dish);
        provideMerchantRepository.save(provideMerchant);

        DishRelationProvide dishRelationProvide = new DishRelationProvide();
        dishRelationProvide.setDishId(result.getId());
        dishRelationProvide.setProvideMerchantId(dishDto.getProvideMerchantId());
        dishRelationProvideRepository.save(dishRelationProvide);

        RePertoryManager rePertoryManager  = new RePertoryManager();
        rePertoryManager.setDishId(result.getId());
        rePertoryManager.setDish(result);
        rePertoryManager.setDishName(result.getName());
        rePertoryManager.setNowCount(1000);
        ProvideDish provideDish = provideDishRepository.findByProvideIdAndName(dishDto.getProvideMerchantId(), result.getName());
        System.err.println(provideDish);
        rePertoryManager.setOrginalPrice(String.valueOf(provideDish.getPrice()));
        rePertoryManager.setSalePrice(dishDto.getDiscountPrice());
        rePertoryManager.setProvideId(dishDto.getProvideMerchantId());
        rePertoryManager.setProvideMerchant(provideMerchant);
        rePertoryManager.setProvideName(provideMerchant.getName());
        rePertoryManager.setTotalSaleCount(0L);
        rePertoryManagerRepository.save(rePertoryManager);

        return result;
    }
}
