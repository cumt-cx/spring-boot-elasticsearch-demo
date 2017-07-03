package qianglovepei;

import com.qianglovepei.Application;
import com.qianglovepei.model.City;
import com.qianglovepei.service.CitySearchRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class cc {
    @Autowired
    private CitySearchRepository citySearchRepository;

    @Autowired
    private Client searchClient;

    @Test
    public void testSaveArticleIndex(){
        City city=new City();
        city.setId(2);
        city.setName("北京");
        city.setDescription("北京政治中心，人很多，空气质量很挫");
        city.setScore((double) 5);

        citySearchRepository.save(city);
    }

    @Test
    public void testSearch(){
        String queryString="name:北京";//搜索关键字
        QueryStringQueryBuilder builder=new QueryStringQueryBuilder(queryString);
        Iterable<City> searchResult = citySearchRepository.search(builder);
        Iterator<City> iterator = searchResult.iterator();
        while(iterator.hasNext()){
            City city = iterator.next();
            System.out.println(city.toString());
        }
    }

    @Test
    public void clientSearch(){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        boolQueryBuilder.must(QueryBuilders.queryStringQuery("name:北京"));
        SearchResponse response = searchClient.prepareSearch("secisland")
                .setTypes("city")
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(boolQueryBuilder)
                .setFrom(0).setSize(1000)
                .execute()
                .actionGet();
        for(SearchHit searchHit : response.getHits().getHits()){
            System.out.println(searchHit.getId());
            System.out.println(searchHit.getSource().get("name"));
            System.out.println(searchHit.getSource().get("description"));
        }
    }
}
