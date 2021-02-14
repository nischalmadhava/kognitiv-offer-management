package com.kognitiv.offermanagement.controller;

import com.kognitiv.offermanagement.dto.OfferDto;
import com.kognitiv.offermanagement.dto.OfferListDto;
import com.kognitiv.offermanagement.entity.Offer;
import com.kognitiv.offermanagement.service.OfferManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

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
    public OfferDto newOffer(@RequestBody OfferDto offerDto) {
        return offerManagementService.createOffer(offerDto);
    }

    @GetMapping("/collect/offer/{name}/page/{page}/size/{size}")
    public OfferListDto getOffersWithPaginationInDateRange(@PathVariable(name = "name") String name,
                                                           @PathVariable(name = "page") Integer page,
                                                           @PathVariable(name = "size") Integer size) throws ParseException {
        return offerManagementService.getOffersWithPaginationWithName(name, page, size);
    }


}
