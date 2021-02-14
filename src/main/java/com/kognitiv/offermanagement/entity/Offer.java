package com.kognitiv.offermanagement.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;
    private Date validFrom;
    private Date validTill;
    private String location;

    @Lob
    private byte[] wImages;

    public Offer() {

    }

    public Offer(Long id, String name, Date validFrom, Date validTill, String location) {
        this.id = id;
        this.name = name;
        this.validFrom = validFrom;
        this.validTill = validTill;
        this.location = location;
    }

    public Offer(String name, Date validFrom, Date validTill, String location) {
        this.name = name;
        this.validFrom = validFrom;
        this.validTill = validTill;
        this.location = location;
    }

    public Offer(String name, Date validFrom, Date validTill, String location, byte[] wImages) {
        this.name = name;
        this.validFrom = validFrom;
        this.validTill = validTill;
        this.location = location;
        this.wImages = wImages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getwImages() {
        return wImages;
    }

    public void setwImages(byte[] wImages) {
        this.wImages = wImages;
    }
}
