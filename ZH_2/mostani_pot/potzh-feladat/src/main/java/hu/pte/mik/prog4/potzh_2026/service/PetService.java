package hu.pte.mik.prog4.potzh_2026.service;

import hu.pte.mik.prog4.potzh_2026.entity.PetEntity;
import hu.pte.mik.prog4.potzh_2026.repository.PetRepository;

import java.util.List;

public class PetService {

    private final PetRepository petRepository = new PetRepository();

    public List<PetEntity> findAll() {
        return petRepository.findAll();
    }

    public PetEntity findById(Long id) {
        return petRepository.findById(id);
    }

    public void save(PetEntity pet) {
        petRepository.save(pet);
    }

    public void update(PetEntity pet) {
        petRepository.update(pet);
    }
}