package jpabook.jpashop.domain.item;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}
