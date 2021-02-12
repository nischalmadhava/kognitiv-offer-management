package com.kognitiv.offermanagement.dto;

import com.kognitiv.offermanagement.entity.Offer;

import java.util.List;

public class OfferDto {

    private Boolean success;
    private List<Offer> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Offer> getData() {
        return data;
    }

    public void setData(List<Offer> data) {
        this.data = data;
    }
    
}
