package com.sesimagotag.training.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sesimagotag.training.demo.entities.Item;

@org.springframework.web.bind.annotation.RestController
public class RestControllerForJavaTechnical {

    /* Can't be changed */
    private Map<String, Item> items = Collections.synchronizedMap(new LinkedHashMap<String, Item>());
    private static ReentrantLock lockForItems = new ReentrantLock();
    private static ReentrantLock lockForItemz = new ReentrantLock();
    /* End can't be changed */

    @GetMapping(value = "api/v1/throwException", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> testALock() {
        /* Can't be changed */
        items.put("lock", null);
        lockForItems.lock();
        try {
            final List<Item> copy = new ArrayList<>(items.values());
            copy.forEach(item -> {
                System.out.println(item.toString());
            });
            lockForItemz.lock();
            /* End can't be changed */
            System.out.println(items.size());
        } finally {
            items.remove("lock");
            lockForItemz.unlock();
            lockForItems.unlock();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /* Can't be changed */
    private final Map<String, AtomicInteger> mapOfInts = new HashMap<>();
    /* End can't be changed */

    @PostMapping(value = "api/v1/incInt/{intId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> incInt(@PathVariable String intId) throws InterruptedException {
        // You must implement something to guarantee that target int will be increment
        // once
        AtomicInteger i = mapOfInts.computeIfAbsent(intId, k -> new AtomicInteger(0));
        synchronized (mapOfInts){
            AtomicInteger ii = mapOfInts.computeIfAbsent(intId, k -> new AtomicInteger(0));
            int currentValue = ii.getAndIncrement();
            Thread.sleep(currentValue * 5000L);
            int newValue = ii.get();

            if (newValue != currentValue + 1) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }

        Thread.sleep(i * 5000l);
        int checkBadly = mapOfInts.getOrDefault(intId, new AtomicInteger.valueOf(0));
        if (checkBadly != i) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        i++;
        mapOfInts.put(intId, Integer.valueOf(i));
        /* End can't be changed */

        return new ResponseEntity<>(Integer.valueOf(i), HttpStatus.OK);
    }






}
