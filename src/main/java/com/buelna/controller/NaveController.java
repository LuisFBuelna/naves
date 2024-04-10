package com.buelna.controller;

import com.buelna.entities.Nave;
import com.buelna.services.NaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/naves")
public class NaveController {

    @Autowired
    private NaveService naveService;

    @GetMapping
    public ResponseEntity<List<Nave>> getTripulantes() {

        return ResponseEntity.ok(naveService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nave> getTripulanteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(naveService.getNaveById(id));
    }

    @PostMapping
    public ResponseEntity<Nave> createTripulante(@RequestBody Nave nave) {
        Nave savedNave = naveService.saveNave(nave);
        return new ResponseEntity<>(savedNave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nave> updateNave(@PathVariable Long id, @RequestBody Nave nave) {
        Nave updatedNave = naveService.updateNave(nave, id);
        return ResponseEntity.ok(updatedNave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNave(@PathVariable Long id) {
        naveService.deleteNave(id);
        return ResponseEntity.noContent().build();
    }
}
