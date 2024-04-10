package com.buelna.dtos;

import com.buelna.entities.Nave;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "tripulante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripulanteDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;

    @Column(name = "registered_at")
    private Date registeredAt;

    @ManyToOne
    @JoinColumn(name = "nave_id", referencedColumnName = "id")
    private Nave nave;

}
