package com.kognitiv.offermanagement.dto;

import java.util.Date;

public class OfferDto {

    private Boolean success;
    private String name;
    private Date validFrom;
    private Date validTill;
    private String location;

    public OfferDto() {

    }

    public OfferDto(String name, Date validFrom, Date validTill, String location) {
        this.name = name;
        this.validFrom = validFrom;
        this.validTill = validTill;
        this.location = location;
    }

    public OfferDto(Boolean success, String name, Date validFrom, Date validTill, String location) {
        this.success = success;
        this.name = name;
        this.validFrom = validFrom;
        this.validTill = validTill;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
