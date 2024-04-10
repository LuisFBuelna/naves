package com.buelna.services;

import com.buelna.dtos.NaveDTO;
import com.buelna.entities.Nave;

import java.util.List;

public interface NaveService {
    List<NaveDTO> getAll();

    NaveDTO getNaveById(Long id);

    Nave saveNave(Nave nave);

    Nave updateNave(Nave nave, Long id);

    void deleteNave(Long id);

    boolean despegar(Long id);
}
