package com.kognitiv.offermanagement.service;

import com.kognitiv.offermanagement.entity.Offer;

import java.util.List;

public interface OfferManagementService {

    List<Offer> getOffers(int pageStartIndex);
}
