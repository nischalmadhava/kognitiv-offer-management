package com.kognitiv.offermanagement.controller;

import com.kognitiv.offermanagement.dto.OfferDto;
import com.kognitiv.offermanagement.dto.OfferListDto;
import com.kognitiv.offermanagement.entity.Offer;
import com.kognitiv.offermanagement.service.OfferManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class OfferManagementController {

    @Autowired
    private OfferManagementService offerManagementService;

    @GetMapping("/collect/offer")
    public OfferListDto getAllOffers() {
        return offerManagementService.getOffers();
    }
    
    @PostMapping("/collect/offer")
    @ResponseStatus(HttpStatus.CREATED)
    public Offer newOffer(@RequestBody OfferDto offerDto) {
        return offerManagementService.createOffer(offerDto);
    }

    @GetMapping("/collect/offer/{validFrom}/to/{validTill}/page/{page}/size/{size}")
    public OfferListDto getOffersWithPaginationInDateRange(@PathVariable("validFrom") @DateTimeFormat(pattern = "dd-MM-yyyy") Date validFrom,
                 @PathVariable("validTill") @DateTimeFormat(pattern = "dd-MM-yyyy") Date validTill, int page, int size) {
        return offerManagementService.getOffersWithPaginationInDateRange(validFrom, validTill, page, size);
    }


}
