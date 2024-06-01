package com.keellssuper.pointofsales2.repo;

import com.keellssuper.pointofsales2.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item,Integer> {
    Item findAllByItemNameEqualsAndActiveStateEquals(String itemName, boolean activeState);


    Page<Item> findAllByActiveStateEquals(boolean activeStatus, PageRequest of);

    long countAllByActiveStateEquals(boolean activeStatus);
}
