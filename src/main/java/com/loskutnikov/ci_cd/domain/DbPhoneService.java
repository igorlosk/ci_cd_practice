package com.loskutnikov.ci_cd.domain;

import com.loskutnikov.ci_cd.api.PhoneDto;
import com.loskutnikov.ci_cd.db.PhoneEntity;
import com.loskutnikov.ci_cd.db.PhoneRepository;
import com.loskutnikov.ci_cd.db.PhoneToEntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbPhoneService implements PhoneService {

    private final PhoneToEntityMapper phoneToEntityMapper;
    private final PhoneRepository phoneRepository;
    private final PhoneToDtoMapper phoneToDtoMapper;

    public DbPhoneService(
            PhoneToEntityMapper phoneToEntityMapper,
            PhoneRepository phoneRepository,
            PhoneToDtoMapper phoneToDtoMapper) {
        this.phoneToEntityMapper = phoneToEntityMapper;
        this.phoneRepository = phoneRepository;
        this.phoneToDtoMapper = phoneToDtoMapper;
    }


    @Override
    public Phone create(PhoneDto phoneDto) {
        Phone phoneToSave = new Phone(
                null,
                phoneDto.brandName(),
                phoneDto.model(),
                phoneDto.price()
        );

        PhoneEntity save = phoneRepository.save(phoneToEntityMapper.toEntity(phoneToSave));

        return phoneToEntityMapper.toDomain(save);

    }

    @Override
    public Phone getById(Long id) {

        PhoneEntity phoneEntity = phoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phone with id=" + id + " not found"));

        return phoneToEntityMapper.toDomain(phoneEntity);
    }

    @Override
    public void deleteById(Long id) {
        PhoneEntity phoneEntity = phoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phone with id=" + id + " not found"));
        phoneRepository.delete(phoneEntity);
    }

    @Override
    public Phone updateById(PhoneDto phoneDto, Long id) {
        PhoneEntity phoneEntity = phoneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Phone with id=" + id + " not found"));
        phoneEntity.setBrandName(phoneDto.brandName());
        phoneEntity.setModel(phoneDto.model());
        phoneEntity.setPrice(phoneDto.price());
        phoneRepository.save(phoneEntity);
        return phoneToEntityMapper.toDomain(phoneEntity);
    }

    @Override
    public List<Phone> getAllPhones() {

        return phoneRepository.findAll()
                .stream()
                .map(phoneToEntityMapper::toDomain)
                .toList();
    }
}
