package com.kognitiv.offermanagement.service;

import com.kognitiv.offermanagement.dto.Offer;
import com.kognitiv.offermanagement.dto.OfferListDto;

import java.util.Date;

public interface OfferManagementService {

    OfferListDto getOffers();

    OfferListDto getOffersWithPaginationWithName(String name, Integer page, Integer size);

    com.kognitiv.offermanagement.entity.Offer createOffer(Offer offer);

}
