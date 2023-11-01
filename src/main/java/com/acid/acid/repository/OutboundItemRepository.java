package com.acid.acid.repository;

import com.acid.acid.entity.OutboundItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundItemRepository extends JpaRepository<OutboundItem, Long> {
}
