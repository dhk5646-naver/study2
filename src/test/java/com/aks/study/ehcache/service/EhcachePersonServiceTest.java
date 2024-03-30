package com.aks.study.ehcache.service;

import com.aks.study.ehcache.constants.EhCacheConfigurationConstants;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@SpringBootTest
public class EhcachePersonServiceTest {

    @Autowired
    private EhcachePersonService ehcachePersonService;

    @Autowired
    private CacheManager cacheManager;

    @AfterEach
    void afterEach() {
        // 각 메소드 마다 호출 후 실행된다.
        cacheManager.getCache(EhCacheConfigurationConstants.PERSON_CACHE).clear();
    }

    @Test
    public void getNonCachedPerson_호출시_캐싱되지않는다() {

        // given
        String name = "악스";

        // when
        ehcachePersonService.getNonCachedPerson(name);

        //then
        Cache.ValueWrapper cache = cacheManager.getCache(EhCacheConfigurationConstants.PERSON_CACHE).get("악스");// 캐싱 조회
        Assertions.assertNull(cache);
    }

    @Test
    public void getCachedPerson_호출시_캐싱된다() {

        // given
        String name = "악스";

        // when
        ehcachePersonService.getCachedPerson(name);

        //then
        Cache.ValueWrapper cache = cacheManager.getCache(EhCacheConfigurationConstants.PERSON_CACHE).get("악스");// 캐싱 조회
        Assertions.assertNotNull(cache);

    }
}
