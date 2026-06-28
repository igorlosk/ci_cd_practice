package com.loskutnikov.ci_cd.domain;

import com.loskutnikov.ci_cd.api.PhoneDto;

import java.util.List;

public interface PhoneService {

    Phone create(PhoneDto phoneDto);
    Phone getById(Long id);
    void deleteById(Long id);
    Phone updateById(PhoneDto phoneDto, Long id);
    List<Phone> getAllPhones();

}
