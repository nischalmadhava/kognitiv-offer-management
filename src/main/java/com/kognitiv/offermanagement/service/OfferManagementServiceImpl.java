package com.kognitiv.offermanagement.service;

import com.kognitiv.offermanagement.entity.Offer;
import com.kognitiv.offermanagement.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OfferManagementServiceImpl implements OfferManagementService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Offer> getOffers(int pageStartIndex) {
        int pageEndIndex = pageStartIndex + 10;
        Pageable entriesInPage = PageRequest.of(pageStartIndex, pageEndIndex);
        Page<Offer> offerPage = offerRepository.findAll(entriesInPage);
        return offerPage.stream().collect(Collectors.toList());
    }
}
