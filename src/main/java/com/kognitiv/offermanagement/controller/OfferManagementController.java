package com.kognitiv.offermanagement.controller;

import com.kognitiv.offermanagement.dto.OfferDto;
import com.kognitiv.offermanagement.dto.OfferListDto;
import com.kognitiv.offermanagement.entity.Offer;
import com.kognitiv.offermanagement.service.OfferManagementService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Api(tags = "offers")
@RestController
public class OfferManagementController {

    @Autowired
    private OfferManagementService offerManagementService;

    @ApiOperation("Retrieves all Offers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK") })
    @GetMapping("/collect/offer")
    public OfferListDto getAllOffers() {
        return offerManagementService.getOffers();
    }


    @ApiOperation("Creates a new Offer.")
    @PostMapping("/collect/offer")
    @ResponseStatus(HttpStatus.CREATED)
    public OfferDto newOffer(
            @ApiParam(name="offer", value = "The Offer", required = true)
            @RequestBody OfferDto offerDto) {
        return offerManagementService.createOffer(offerDto);
    }

    @ApiOperation("Retrieves Offers by Name with Pagination")
    @GetMapping("/collect/offer/{name}/page/{page}/size/{size}")
    public OfferListDto getOffersWithPaginationInDateRange(
            @ApiParam(name="name", value = "The name of the offer", required = true)
            @PathVariable(name = "name") String name,
            @ApiParam(name="page", value = "Indicates which page to get. Ex : 1", required = true)
            @PathVariable(name = "page") Integer page,
            @ApiParam(name="size", value = "Indicates how many offers to retrieve per page", required = true)
            @PathVariable(name = "size") Integer size) throws ParseException {
        return offerManagementService.getOffersWithPaginationWithName(name, page, size);
    }


}
