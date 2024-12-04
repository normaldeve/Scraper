package com.example.demo.service;

import com.example.demo.model.Company;
import com.example.demo.model.Dividend;
import com.example.demo.model.ScrapedResult;
import com.example.demo.persist.CompanyRepository;
import com.example.demo.persist.DividendRepository;
import com.example.demo.persist.entity.CompanyEntity;
import com.example.demo.persist.entity.DividendEntity;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class FinanceService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;

    //@Cacheable(key = "#companyName", value = "finance")
    public ScrapedResult getDividendByCompanyName(String companyName) {
        // 1. 회사명을 기준으로 회사 정보를 조회하기
        CompanyEntity company = companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명입니다."));
        // 2. 조회된 회사 ID로 배당금 정보를 조회
        List<DividendEntity> dividendEntities = dividendRepository.findAllByCompanyId(company.getId());
        // 3. 결과 조합 후 반환하기
        List<Dividend> dividends = new ArrayList<>();
        for (var entity : dividendEntities) {
            dividends.add(Dividend.builder()
                    .date(entity.getDate())
                    .dividend(entity.getDividend())
                    .build());
        }

        return new ScrapedResult(Company.builder()
                .ticker(company.getTicker())
                .name(company.getName())
                .build(), dividends);
    }
}
