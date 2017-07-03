package com.qianglovepei.service;

import com.qianglovepei.model.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CitySearchRepository extends ElasticsearchRepository<City, Long> {

}
