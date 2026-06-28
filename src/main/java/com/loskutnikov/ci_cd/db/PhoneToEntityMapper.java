package com.loskutnikov.ci_cd.db;

import com.loskutnikov.ci_cd.domain.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneToEntityMapper {

    public PhoneEntity toEntity(Phone phone) {
        return new PhoneEntity(
                phone.id(),
                phone.brandName(),
                phone.model(),
                phone.price()
        );
    }

    public Phone toDomain(PhoneEntity phoneEntity){
        return new Phone(
                phoneEntity.getId(),
                phoneEntity.brandName,
                phoneEntity.model,
                phoneEntity.price
        );
    }
}
