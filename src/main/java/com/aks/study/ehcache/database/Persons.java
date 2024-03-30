package com.aks.study.ehcache.database;

import com.aks.study.ehcache.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Persons {

    private static final List<Person> persons = new ArrayList<>();

    static {
        // 테스트 목적으로 데이터를 초기화 한다.
        persons.add(Person.create("악스"));
    }

    public static Person getPerson(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        return persons.stream()
                .filter(person -> person.getName().equals(name))
                .findFirst()
                .orElseGet(Person::empty);
    }
}
