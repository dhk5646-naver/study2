package com.aks.study.ehcache.service;

import com.aks.study.ehcache.constants.EhCacheConfigurationConstants;
import com.aks.study.ehcache.entity.Person;
import com.aks.study.ehcache.repository.EhcachePersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EhcachePersonService {

    private final EhcachePersonRepository ehcachePersonRepository;

    public Person getNonCachedPerson(String name) {
        return ehcachePersonRepository.getPerson(name);
    }

    @Cacheable(cacheNames= EhCacheConfigurationConstants.PERSON_CACHE)
    public Person getCachedPerson(String name) {
        return ehcachePersonRepository.getPerson(name);
    }


}
