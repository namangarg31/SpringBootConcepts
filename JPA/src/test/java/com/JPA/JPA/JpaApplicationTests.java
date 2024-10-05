package com.JPA.JPA;

import com.JPA.JPA.entities.ItemEntity;
import com.JPA.JPA.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaApplicationTests {

	@Autowired
	ItemRepository itemRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ItemEntity itemEntity = ItemEntity.builder()
				.sku("cola1234")
				.title("Coca-Cola")
				.price(BigDecimal.valueOf(40.5))
				.quantity(10)
				.build();

		ItemEntity savedItemEntity = itemRepository.save(itemEntity);
		System.out.println(savedItemEntity);
	}

	@Test
	void getRepository() {
		List<ItemEntity> itemEntityList = itemRepository.findByCreatedAtAfter(
				LocalDateTime.of(2024,1,1,0,0,0));
		System.out.println(itemEntityList);

		Optional<ItemEntity> itemEntity = itemRepository.findByTitleAndPrice("Milky Bar",BigDecimal.valueOf(10.50));
		System.out.println(itemEntity);
	}
}
