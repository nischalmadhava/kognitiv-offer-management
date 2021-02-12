package com.kognitiv.offermanagement.repository;

import com.kognitiv.offermanagement.entity.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {


}
