package com.nikolastrapp.projeto.services;

import com.nikolastrapp.projeto.entities.ParkingSpot;
import com.nikolastrapp.projeto.repositories.ParkingSpotRepository;
import com.nikolastrapp.projeto.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;


    public List<ParkingSpot> queryAll() {
        return parkingSpotRepository.findAll();
    }

    public ParkingSpot queryById(Long id) {
        return parkingSpotRepository.getReferenceById(id);
    }

    public ParkingSpot insert(ParkingSpot e) {
        return parkingSpotRepository.save(e);
    }

    public List<ParkingSpot> insertAll(List<ParkingSpot> categories) {
        return parkingSpotRepository.saveAll(categories);
    }

    public void delete(Long id) {
        try {
            parkingSpotRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public ParkingSpot update(ParkingSpot entidade, Long id) {
        try {

            ParkingSpot old = parkingSpotRepository.getReferenceById(id);
            updateData(entidade, old);
            return parkingSpotRepository.save(old);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(ParkingSpot entidade, ParkingSpot old) {
        if (!entidade.getName().isBlank()) {
            old.setName(entidade.getName());
        }
    }

}
