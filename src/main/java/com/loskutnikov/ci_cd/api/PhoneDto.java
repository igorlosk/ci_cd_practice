package com.loskutnikov.ci_cd.api;

import java.math.BigDecimal;

public record PhoneDto(
        Long id,
        String brandName,
        String model,
        BigDecimal price
) {
}
