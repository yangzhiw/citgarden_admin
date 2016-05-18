package com.citygarden.service;

import com.citygarden.domain.Dish;
import com.citygarden.domain.ProvideDish;
import com.citygarden.domain.ProvideMerchant;
import com.citygarden.repository.ProvideMerchantRepository;
import com.citygarden.web.rest.ProvideMerchantDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
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


    public ProvideMerchantDTO findOne(String id) throws Exception{
        ProvideMerchant provideMerchant = provideMerchantRepository.findOne(id);
        ProvideMerchantDTO provideMerchantDTO = new ProvideMerchantDTO();
        provideMerchantDTO.setId(provideMerchant.getId());
        provideMerchantDTO.setChineseName(provideMerchant.getChineseName());
        provideMerchantDTO.setDescription(provideMerchant.getDescription());
        List<ProvideDish> provideDishs = provideMerchant.getProvideDishs();
        List<Dish> saleDishs = provideMerchant.getDishs();

        provideMerchantDTO.setProvidePhoto(provideMerchantPhotoService.getProvidePhoto(provideMerchant.getName()));

        return  provideMerchantDTO;
    }
}
