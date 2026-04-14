package hu.pte.mik.gyakzh1.service;

import hu.pte.mik.gyakzh1.model.Phone;
import hu.pte.mik.gyakzh1.repository.PhoneRepository;

import java.util.List;

public class PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneService() {
        this.phoneRepository = PhoneRepository.getInstance();
    }

    public Phone findById(Long id) {
        return this.phoneRepository.findById(id);
    }

    public List<Phone> findAll() {
        return this.phoneRepository.findAll();
    }

    public Phone save(Long id, String manufacturer, String type, String imei) {
        if (id == null) {
            return this.phoneRepository.create(manufacturer, type, imei);
        } else {
            return this.phoneRepository.update(new Phone(id, manufacturer, type, imei));
        }
    }

    public Phone delete(Long id) {
        return this.phoneRepository.delete(id);
    }
}
