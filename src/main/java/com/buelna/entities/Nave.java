package com.buelna.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "take_off")
    private boolean takeOff;
}
