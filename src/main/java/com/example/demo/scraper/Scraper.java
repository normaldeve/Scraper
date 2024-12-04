package com.example.demo.scraper;

import com.example.demo.model.Company;
import com.example.demo.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);

    ScrapedResult scrap(Company company);
}
