package hu.pte.mik.zhgyak3.service;

import hu.pte.mik.zhgyak3.model.Pet;
import hu.pte.mik.zhgyak3.repository.PetRepository;

import java.util.List;
import java.util.Optional;

public class PetService {

	private final PetRepository petRepository;

	public PetService() {
		this.petRepository = PetRepository.getInstance();
	}

	public Pet save(Pet pet) {
		if (pet.getId() == null) {
			return this.petRepository.save(pet);
		} else {
			return this.petRepository.update(pet);
		}
	}

	public List<Pet> findAll() {
		return this.petRepository.listAll();
	}

	public Optional<Pet> findById(Long id) {
		return this.petRepository.findById(id);
	}

	public int getGlobalIdentifier(Pet pet) {
		return pet.hashCode();
	}

	public boolean deletePet(Long id) {
		return this.petRepository.delete(id);
	}

}
