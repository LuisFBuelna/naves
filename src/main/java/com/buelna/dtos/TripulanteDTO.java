package com.buelna.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripulanteDTO {

    private Long id;
    private String name;
    private String role;

    @Column(name = "registered_at")
    private Date registeredAt;

    @Column(name = "nave_id")
    private Long naveId;

}
