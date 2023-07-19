package com.sesimagotag.training.demo;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sesimagotag.training.demo.entities.Item;

@org.springframework.web.bind.annotation.RestController
public class RestControllerForItems {
    private final List<Item> itemList = new ArrayList<>();

    /**
     * Create all items given in parameters and overwrite existing one.
     *
     * @return count of new items added
     */
    @PostMapping(value = "api/v1/items", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createItems(@RequestBody final List<Item> newItems) {
        int itemsAddedCount = 0;
        for (Item item : newItems) {
            // check for  duplicate requests with the same items in the requestBody
            boolean ItemExists = itemList.stream().anyMatch(oldItem -> oldItem.getId().equals(item.getId()));
            if (!ItemExists) {
                itemList.add(item);
                itemsAddedCount++;
            }

        }
        return new ResponseEntity<>(itemsAddedCount, HttpStatus.OK);
    }

    /**
     * @return return item with corresponding itemId
     */
    @GetMapping(value = "api/v1/items/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItem(@PathVariable("itemId") final String itemId) {
        for (Item item : itemList) {
            if (item.getId().equals(itemId)) {
                return new ResponseEntity<>(item, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Do not modify existing item list on the reverse operation.
     *
     * @return all items with reverse name
     */
    @GetMapping(value = "api/v1/items/{itemId}/reverse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItemsReverse(@PathVariable String itemId) {
        for (Item item : itemList) {
            if (item.getId().equals(itemId)) {
                StringBuilder sb = new StringBuilder();
                String reversedName = sb.append(item.getName()).reverse().toString();
                Item ItemWithReversedName = new Item(item.getId(), item.getPrice(), reversedName);
                return new ResponseEntity<>(ItemWithReversedName,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * @return all items sorted by prices asc and names asc
     */
    @GetMapping(value = "api/v1/items/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItemsSort() {
        return null;
    }

    /**
     * @return all items
     */
    @GetMapping(value = "api/v1/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItems() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * <p>
     * page=1 and pageSize=5, return 0->4 elements
     * </p>
     * <p>
     * page=2 and pageSize=5, return 5->9 elements
     * </p>
     * <p>
     * page=2 and pageSize=10, return 10->19 elements
     * </p>
     *
     * @param page
     *                    first page, start at 1
     * @param pageSize
     *                    elements returned in a page
     * @param sort
     *                    sort by prices asc and names asc
     * @param reverseName
     *                    reverse names (after sorting)
     * @return items
     */


}
