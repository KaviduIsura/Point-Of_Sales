package com.keellssuper.pointofsales2.repo;

import com.keellssuper.pointofsales2.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    List<Customer>findAllByActiveEquals(boolean activeState);

    Page<Customer> findAllByActiveEquals(boolean activeStatus, PageRequest of);

    long countAllByActiveEquals(boolean activeStatus);
}
