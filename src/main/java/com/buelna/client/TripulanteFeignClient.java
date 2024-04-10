package com.buelna.client;

import com.buelna.dtos.TripulanteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "tripulanteClient", url = "http://localhost:8081/tripulantes")
public interface TripulanteFeignClient {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    List<TripulanteDTO> getAllTripulantes();
}
