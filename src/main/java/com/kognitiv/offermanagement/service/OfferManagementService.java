package com.kognitiv.offermanagement.service;


import com.kognitiv.offermanagement.dto.OfferDto;
import com.kognitiv.offermanagement.dto.OfferListDto;
import com.kognitiv.offermanagement.entity.Offer;

import java.util.Date;

public interface OfferManagementService {

    OfferListDto getOffers();

    OfferListDto getOffersWithPaginationWithName(String name, Integer page, Integer size);

    OfferDto createOffer(OfferDto offer);

}
