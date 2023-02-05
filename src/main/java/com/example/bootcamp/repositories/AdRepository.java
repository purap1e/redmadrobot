package com.example.bootcamp.repositories;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.response.AdDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface AdRepository extends JpaRepository<Ad,Long> {
    List<Ad> findAdsByPriceGreaterThanAndPriceLessThan(int min, int max, Pageable pageable);
}
