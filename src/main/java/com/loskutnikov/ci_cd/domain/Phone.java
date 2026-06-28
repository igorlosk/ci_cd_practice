package com.loskutnikov.ci_cd.domain;

import java.math.BigDecimal;

public record Phone(
        Long id,
        String brandName,
        String model,
        BigDecimal price
) {
}
