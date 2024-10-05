package com.JPA.JPA.controllers;

import com.JPA.JPA.entities.ItemEntity;
import com.JPA.JPA.repositories.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final Integer PAGE_SIZE = 5;
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<ItemEntity> getAllItems(
            @RequestParam(defaultValue = "1") Integer quantity,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber) {
        return itemRepository.findByQuantity(
                quantity,
                PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Direction.DESC, sortBy, "price")));
    }
}
