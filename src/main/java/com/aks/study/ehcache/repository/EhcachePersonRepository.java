package com.aks.study.ehcache.repository;

import com.aks.study.ehcache.database.Persons;
import com.aks.study.ehcache.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class EhcachePersonRepository {

    public Person getPerson(String name) {
        return Persons.getPerson(name);
    }
}
