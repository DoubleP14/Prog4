package hu.pte.mik.prog4.potpotzh_2026.service;

import hu.pte.mik.prog4.potpotzh_2026.entity.Hallgato;
import hu.pte.mik.prog4.potpotzh_2026.repository.HallgatoRepository;

import java.util.List;

public class HallgatoService {
    private final HallgatoRepository repository = new HallgatoRepository();

    public List<Hallgato> getAllHallgatok() {
        return repository.findAllOrderByName();
    }

    public Hallgato getHallgatoById(int id) {
        return repository.findById(id);
    }

    public void createHallgato(Hallgato hallgato) {
        repository.save(hallgato);
    }

    public void deleteHallgato(int id) {
        repository.deleteById(id);
    }
}
