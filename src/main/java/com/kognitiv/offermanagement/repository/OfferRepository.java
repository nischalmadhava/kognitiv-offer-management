package com.kognitiv.offermanagement.repository;

import com.kognitiv.offermanagement.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Page<Offer> findByValidFromAfterAndValidTillBefore(Date validFrom, Date validTill, Pageable pageable);

}
