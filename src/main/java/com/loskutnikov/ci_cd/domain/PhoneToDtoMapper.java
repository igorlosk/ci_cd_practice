package com.loskutnikov.ci_cd.domain;

import com.loskutnikov.ci_cd.api.PhoneDto;
import org.springframework.stereotype.Component;

@Component
public class PhoneToDtoMapper {

    public Phone toDomain(PhoneDto phoneDto) {
        return new Phone(
                phoneDto.id(),
                phoneDto.brandName(),
                phoneDto.model(),
                phoneDto.price()
        );
    }

    public PhoneDto toDto(Phone phone){
        return new PhoneDto(
                phone.id(),
                phone.brandName(),
                phone.model(),
                phone.price()
        );
    }
}
