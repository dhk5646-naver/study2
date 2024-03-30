package com.aks.study.ehcache.entity;


import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Person implements Serializable {
    private String id;
    private String name;

    public static Person create(String code) {
        return new Person(generateId(), code);
    }

    public static Person empty() {
        return new Person();
    }

    private static String generateId() {
        return UUID.randomUUID().toString();
    }
}
