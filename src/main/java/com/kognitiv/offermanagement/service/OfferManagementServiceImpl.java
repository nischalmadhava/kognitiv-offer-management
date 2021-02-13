package com.kognitiv.offermanagement.service;

import com.kognitiv.offermanagement.dto.OfferDto;
import com.kognitiv.offermanagement.dto.OfferListDto;
import com.kognitiv.offermanagement.entity.Offer;
import com.kognitiv.offermanagement.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Date;
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
    public OfferListDto getOffersWithPaginationInDateRange(Date validFrom, Date validTill, int page, int size) {
        int pageStartIndex = 0;
        int pageEndIndex = size - 1;

        if (page != 1) {
            pageStartIndex = (page - 1) * size;
            pageEndIndex = pageStartIndex + 10;
        }

        Pageable entriesInPage = PageRequest.of(pageStartIndex, pageEndIndex);
        Page<Offer> offerPage = offerRepository.findByValidFromAfterAndValidTillBefore(validFrom, validTill, entriesInPage);

        OfferListDto offerListDto = new OfferListDto();
        offerListDto.setSuccess(true);
        offerListDto.setData(offerPage.stream().collect(Collectors.toList()));

        return offerListDto;
    }

    @Override
    public Offer createOffer(OfferDto offerDto) {
        return offerRepository.save(new Offer(offerDto.getName(), offerDto.getValidFrom(), offerDto.getValidTill(), offerDto.getLocation()));
    }

}
