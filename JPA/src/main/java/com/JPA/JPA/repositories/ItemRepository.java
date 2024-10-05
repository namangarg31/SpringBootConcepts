package com.JPA.JPA.repositories;

import com.JPA.JPA.entities.ItemEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByCreatedAtAfter(LocalDateTime after);
    @Query("select e from ItemEntity e where e.title=:title and e.price=:price")
    Optional<ItemEntity> findByTitleAndPrice(String title, BigDecimal price);
    List<ItemEntity> findByQuantity(Integer quantity, PageRequest price);
}
