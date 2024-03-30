package com.aks.study.ehcache.config;

import com.aks.study.ehcache.constants.EhCacheConfigurationConstants;
import com.aks.study.ehcache.entity.Person;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import java.util.Map;


@EnableCaching
@Configuration
public class EhcacheConfig {

    @Bean
    public CacheManager cacheManager() {

        // jcache manager 를 생성한다.
        javax.cache.CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();

        // ehcache configuration 을 생성한다.
        CacheConfiguration<String, Person> personEhCacheConfiguration = createPersonEhCacheConfiguration();

        // ehcache configuration 을 jcache configuration 으로 변환한다.
        javax.cache.configuration.Configuration<String, Person> personJCachConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(personEhCacheConfiguration);

        // jcache 를 생성한다.
        cacheManager.createCache(EhCacheConfigurationConstants.PERSON_CACHE, personJCachConfiguration);

        // jcache manager 를 spring cache manager 로 변환한다.
        return new JCacheCacheManager(cacheManager);
    }

    private CacheConfiguration<String, Person> createPersonEhCacheConfiguration() {
        return CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, Person.class, ResourcePoolsBuilder.heap(10000L))
                .withDispatcherConcurrency(4)
                .withDefaultEventListenersThreadPool()
                .build();
    }


//    /**
//     * ehcache 제공자를 이용하여 ehcache 설정을 적용한 최종 결과물을 이용해서 jcache manager 를 생성한다.
//     * */
//    @Bean
//    public CacheManager cacheManager() {
//        // ehcache 제공자를 생성한다.
//        EhcacheCachingProvider ehcacheCachingProvider = (EhcacheCachingProvider) Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
//
//        // ehcache configuration 목록을 생성한다.
//        Map<String, CacheConfiguration<?, ?>> cacheConfigurationMap = getCacheConfigurationMap();
//
//        // ehcache 제공자와 ehcache 목록을 이용하여 ehcache 기본 설정 객체를 생성한다.
//        DefaultConfiguration defaultConfiguration = new DefaultConfiguration(cacheConfigurationMap, ehcacheCachingProvider.getDefaultClassLoader());
//
//        // ehcache 제공자를 이용하여 jache manager 를 생성한다.
//        javax.cache.CacheManager cacheManager = ehcacheCachingProvider.getCacheManager(ehcacheCachingProvider.getDefaultURI(), defaultConfiguration);
//
//        // jcache manager 를 spring cache manager 로 변환한다.
//        return new JCacheCacheManager(cacheManager);
//    }
//
//    private Map<String, CacheConfiguration<?, ?>> getCacheConfigurationMap() {
//        Map<String, CacheConfiguration<?, ?>> cacheMap = new HashMap<>();
//        cacheMap.put(EhCacheConfigurationConstants.PERSON_CACHE, createPersonEhCacheConfiguration());
//
//        return cacheMap;
//    }

}
