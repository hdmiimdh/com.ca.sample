package com.ca.sample.repository;

import com.ca.sample.domain.VendingItem;
import com.ca.sample.domain.VendingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendingRepository extends JpaRepository<VendingItem, Long> {

    long countByType(VendingType type);

    VendingItem findFirstByType(VendingType type);
}
