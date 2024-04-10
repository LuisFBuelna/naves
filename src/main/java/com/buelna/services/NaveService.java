package com.buelna.services;

import com.buelna.entities.Nave;

import java.util.List;

public interface NaveService {
    List<Nave> getAll();

    Nave getNaveById(Long id);

    Nave saveNave(Nave nave);

    Nave updateNave(Nave nave, Long id);

    void deleteNave(Long id);
}
