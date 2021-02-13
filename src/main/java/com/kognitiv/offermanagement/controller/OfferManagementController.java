package com.kognitiv.offermanagement.controller;

import com.kognitiv.offermanagement.dto.Offer;
import com.kognitiv.offermanagement.dto.OfferListDto;
import com.kognitiv.offermanagement.service.OfferManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public com.kognitiv.offermanagement.entity.Offer newOffer(@RequestBody Offer offer) {
        return offerManagementService.createOffer(offer);
    }

    @GetMapping("/collect/offer/{name}/page/{page}/size/{size}")
    public OfferListDto getOffersWithPaginationInDateRange(@PathVariable(name = "name") String name,
                                                           @PathVariable(name = "page") Integer page,
                                                           @PathVariable(name = "size") Integer size) throws ParseException {
        return offerManagementService.getOffersWithPaginationWithName(name, page, size);
    }


}
