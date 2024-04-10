package com.buelna.services.implement;

import com.buelna.client.TripulanteFeignClient;
import com.buelna.dtos.NaveDTO;
import com.buelna.dtos.TripulanteDTO;
import com.buelna.entities.Nave;
import com.buelna.exceptions.BadRequestException;
import com.buelna.exceptions.NaveSinTripulantesException;
import com.buelna.exceptions.NotFoundException;
import com.buelna.mapper.NaveMapper;
import com.buelna.repository.NaveRepository;
import com.buelna.services.NaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NaveServiceImpl implements NaveService {

    @Autowired
    private NaveRepository naveRepository;

    @Autowired
    private TripulanteFeignClient tripulateClient;

    @Override
    public List<NaveDTO> getAll() {
        List<Nave> listaNaves = naveRepository.findAll();
        List<NaveDTO> listaDto = NaveMapper.mapper.listaToListaDto(listaNaves);

        for (NaveDTO naveDTO : listaDto) {
            agregarTripulantesANaves(naveDTO);
        }
        return listaDto;
    }

    @Override
    public NaveDTO getNaveById(Long id) {
        Nave naveById = naveRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La nave no fue encontrada"));
        NaveDTO naveDTO = NaveMapper.mapper.naveToNaveDto(naveById);

        agregarTripulantesANaves(naveDTO);
        return naveDTO;
    }

    @Override
    public Nave saveNave(Nave nave) {
        try {
            nave.setTakeOff(false);
            return naveRepository.save(nave);
        } catch (Exception exception) {
            throw new BadRequestException("Ha ocurrido un problema con la aplicacion");
        }
    }

    @Override
    public Nave updateNave(Nave nave, Long id) {
        try {
            Optional<Nave> findNave = naveRepository.findById(id);

            if (findNave.isEmpty()) {
                throw new NotFoundException("La nave no fue encontrada");
            }

            Nave updateNave = findNave.get();
            updateNave.setName(nave.getName());
            updateNave.setModel(nave.getModel());
            updateNave.setTakeOff(nave.isTakeOff());

            return naveRepository.save(updateNave);
        } catch (Exception exception) {
            throw new BadRequestException("Ha ocurrido un problema con la aplicacion");
        }
    }

    @Override
    public void deleteNave(Long id) {
        Optional<Nave> findTripulante = naveRepository.findById(id);

        if (findTripulante.isEmpty()) {
            throw new NotFoundException("La nave no fue encontrada");
        }
        naveRepository.deleteById(id);
    }

    @Override
    public boolean despegar(Long id) {
        Optional<Nave> optionalNave = naveRepository.findById(id);
        if (optionalNave.isEmpty()) {
            throw new NotFoundException("La nave no fue encontrada");
        }

        Nave nave = optionalNave.get();
        List<TripulanteDTO> tripulantes = tripulateClient.getAllTripulantes();

        if (tripulantes.stream().anyMatch(tripulante -> tripulante.getNaveId().equals(nave.getId()))) {

            long numTripulantes = tripulantes.stream().filter(tripulante -> tripulante.getNaveId().equals(nave.getId())).count();
            if (numTripulantes >= 1 && numTripulantes <= 3) {

                nave.setTakeOff(true);
                naveRepository.save(nave);
                return true;

            } else {
                throw new NaveSinTripulantesException("La nave debe tener entre 1 y 3 tripulantes asignados para despegar");
            }
        } else {
            throw new NaveSinTripulantesException("No se puede despegar la nave sin tripulantes asignados");
        }
    }

    private void agregarTripulantesANaves(NaveDTO naveDTO) {
        List<TripulanteDTO> tripulantesNave = new ArrayList<>();
        for (TripulanteDTO tripulanteDTO : tripulateClient.getAllTripulantes()) {
            if (tripulanteDTO.getNaveId().equals(naveDTO.getId())) {
                tripulantesNave.add(tripulanteDTO);
            }
        }
        naveDTO.setTripulantes(tripulantesNave);
    }
}
