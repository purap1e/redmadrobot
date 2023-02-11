package com.example.bootcamp.repositories;

import com.example.bootcamp.entities.Ad;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AdRepository extends JpaRepository<Ad,Long> {
    List<Ad> findAdsByPriceGreaterThanAndPriceLessThanAndActiveIs(int min, int max, Pageable pageable, boolean isActive);
    Optional<Ad> findById(Long id);
}
