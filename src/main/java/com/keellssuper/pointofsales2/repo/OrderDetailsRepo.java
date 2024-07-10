package com.keellssuper.pointofsales2.repo;

import com.keellssuper.pointofsales2.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {
}
