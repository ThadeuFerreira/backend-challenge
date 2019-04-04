package com.invillia.acme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StoreController {
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public List<Store> retrieveStores() {return storeRepository.findAll();}

    @GetMapping("/stores/{id}")
    public Store retrieveStoreById(@PathVariable Integer id){
        Optional<Store> storeOptional = storeRepository.findById(id);

        if(!storeOptional.isPresent())
            throw new RuntimeException("Could not find store");

        return storeOptional.get();
    }

    @PostMapping("/store")
    public ResponseEntity<Object> createStore(@RequestBody Store store){
        Store savedStore = storeRepository.save(store);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedStore.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
