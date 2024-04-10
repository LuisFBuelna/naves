package com.buelna.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NaveDTO implements Serializable {

    private Long id;
    private String name;
    private String model;
    private boolean takeOff;
    private List<TripulanteDTO> tripulantes;
}
