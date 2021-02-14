package com.kognitiv.offermanagement.service;

import com.kognitiv.offermanagement.dto.OfferDto;
import com.kognitiv.offermanagement.dto.OfferListDto;
import com.kognitiv.offermanagement.repository.OfferRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.kognitiv.offermanagement.entity.Offer;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Component
public class OfferManagementServiceImpl implements OfferManagementService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public OfferListDto getOffers() {
        OfferListDto offerListDto = new OfferListDto();

        offerListDto.setData(offerRepository.findAll());
        offerListDto.setSuccess(true);

        return offerListDto;
    }

    @Override
    public OfferListDto getOffersWithPaginationWithName(String name, Integer page, Integer size) {
        int pageStartIndex = 0;
        int pageEndIndex;

        System.out.println(page+","+size);

        pageEndIndex = size - 1;

        if (page != 1) {
            pageStartIndex = (page - 1) * size;
            pageEndIndex = pageStartIndex + 10;
        }

        Pageable entriesInPage = PageRequest.of(pageStartIndex, pageEndIndex);
        Page<com.kognitiv.offermanagement.entity.Offer> offerPage = offerRepository.findByName(name, entriesInPage);

        OfferListDto offerListDto = new OfferListDto();
        offerListDto.setSuccess(true);
        offerListDto.setData(offerPage.stream().collect(Collectors.toList()));

        return offerListDto;
    }

    @Override
    public OfferDto createOffer(OfferDto offerDto) {
        RestTemplate restTemplate = new RestTemplate();
        String typicodeUrl
                = "https://jsonplaceholder.typicode.com/photos";
        ResponseEntity<String> typicodeResponse
                = restTemplate.getForEntity(typicodeUrl + "/1", String.class);

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(typicodeResponse.getBody());
        }catch (JSONException err){
            err.printStackTrace();
        }

        String imageUrl = jsonObject.get("url").toString();

        byte[] imageBytes = restTemplate.getForObject(imageUrl, byte[].class);

        Offer offer = offerRepository.save(new Offer(offerDto.getName(), offerDto.getValidFrom(), offerDto.getValidTill(), offerDto.getLocation(), imageBytes));

        OfferDto savedOffer = new OfferDto(true, offer.getName(), offer.getValidFrom(), offer.getValidTill(), offer.getLocation());

        return savedOffer;
    }

}
