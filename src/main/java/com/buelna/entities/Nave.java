package com.buelna.entities;

import com.buelna.dtos.TripulanteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "nave")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String model;

    @OneToMany(mappedBy = "nave")
    private List<TripulanteDTO> tripulantes;

}
