package com.citygarden.service;

import com.citygarden.domain.ProvideDish;
import com.citygarden.domain.ProvideMerchant;
import com.citygarden.repository.ProvideDishRepository;
import com.citygarden.repository.ProvideMerchantRepository;
import com.citygarden.web.rest.dto.ProvideDishDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
@Service
public class ProvideDishService {

    @Inject
    private ProvideDishRepository provideDishRepository;

    @Inject
    private ProvideMerchantRepository provideMerchantRepository;


    public ProvideDish save(ProvideDishDTO provideDishDTO) {
        System.err.println(provideDishDTO);
        ProvideDish provideDish = new ProvideDish();
        provideDish.setName(provideDishDTO.getName());
        provideDish.setPrice(provideDishDTO.getPrice());
        provideDish.setProvideId(provideDishDTO.getProvideMerchantId());
        provideDish.setChineseName(provideDishDTO.getChineseName());
        ProvideDish result = provideDishRepository.save(provideDish);
        ProvideMerchant provideMerchant = provideMerchantRepository.findOne(provideDishDTO.getProvideMerchantId());
        provideMerchant.getProvideDishs().add(result);
        provideMerchantRepository.save(provideMerchant);
        return result;
    }
}
