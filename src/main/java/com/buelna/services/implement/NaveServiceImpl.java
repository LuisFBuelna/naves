package com.buelna.services.implement;

import com.buelna.entities.Nave;
import com.buelna.exceptions.NotFoundException;
import com.buelna.repository.NaveRepository;
import com.buelna.services.NaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NaveServiceImpl implements NaveService {

    @Autowired
    private NaveRepository naveRepository;

    @Override
    public List<Nave> getAll() {
        return naveRepository.findAll();
    }

    @Override
    public Nave getNaveById(Long id) {
        return naveRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El tripulante no fue encontrado"));
    }

    @Override
    public Nave saveNave(Nave nave) {
        try {
            return naveRepository.save(nave);
        } catch (Exception exception) {
            throw new RuntimeException("Ha ocurrido un problema con la aplicacion");
        }
    }

    @Override
    public Nave updateNave(Nave nave, Long id) {
        try {
            Optional<Nave> findNave = naveRepository.findById(id);

            if (findNave.isEmpty()) {
                throw new NotFoundException("El tripulante no fue encontrado");
            }

            Nave updateNave = findNave.get();
            updateNave.setName(nave.getName());
            updateNave.setModel(nave.getModel());
            updateNave.setTripulantes(nave.getTripulantes());

            return naveRepository.save(updateNave);
        } catch (Exception exception) {
            throw new RuntimeException("Ha ocurrido un problema con la aplicacion");
        }
    }

    @Override
    public void deleteNave(Long id) {
        Optional<Nave> findTripulante = naveRepository.findById(id);

        if (findTripulante.isEmpty()) {
            throw new NotFoundException("El tripulante no fue encontrado");
        }
        naveRepository.deleteById(id);
    }
}
