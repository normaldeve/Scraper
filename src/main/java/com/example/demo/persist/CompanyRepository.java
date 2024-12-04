package com.example.demo.persist;

import com.example.demo.persist.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    boolean existsByTicker(String ticker);

    Optional<CompanyEntity> findByName(String Name); // 값이 없을 때 처리할 수 있다는 장점이 존재함

    Page<CompanyEntity> findByNameStartingWithIgnoreCase(String s, Pageable pageable);

}

