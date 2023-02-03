package com.example.bootcamp.repositories;

import com.example.bootcamp.entities.Ad;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface AdRepository extends JpaRepository<Ad,Long> {

//    List<Ad> findAllByActiveIs(boolean isActive);
    List<Ad> findAdsByPriceLessThanAndPriceGreaterThan(int min, int max);
//    List<Ad> findByTitleOrderBy
}
