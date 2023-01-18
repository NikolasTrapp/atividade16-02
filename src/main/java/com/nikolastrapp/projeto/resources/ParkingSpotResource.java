package com.nikolastrapp.projeto.resources;


import com.nikolastrapp.projeto.entities.ParkingSpot;
import com.nikolastrapp.projeto.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/entidade")
public class ParkingSpotResource {

    @Autowired
    private ParkingSpotService parkingSpotService;


    @GetMapping
    public ResponseEntity<List<ParkingSpot>> findAll() {
        List<ParkingSpot> list = parkingSpotService.queryAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParkingSpot> findById(@PathVariable Long id) {
        ParkingSpot entidade = parkingSpotService.queryById(id);
        return ResponseEntity.ok().body(entidade);

    }

    @PostMapping(value = "/add")
    public ResponseEntity<ParkingSpot> add(@RequestBody ParkingSpot entidade) {
        return ResponseEntity.ok().body(parkingSpotService.insert(entidade));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        parkingSpotService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<ParkingSpot> update(@PathVariable Long id, @RequestBody ParkingSpot entidade) {
        entidade = parkingSpotService.update(entidade, id);
        return ResponseEntity.ok().body(entidade);
    }


}
