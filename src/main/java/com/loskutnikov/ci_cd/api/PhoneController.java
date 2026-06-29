package com.loskutnikov.ci_cd.api;

import com.loskutnikov.ci_cd.domain.DbPhoneService;
import com.loskutnikov.ci_cd.domain.Phone;
import com.loskutnikov.ci_cd.domain.PhoneToDtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phone")
public class PhoneController {

    private final DbPhoneService dbPhoneService;
    private final PhoneToDtoMapper phoneToDtoMapper;

    public PhoneController(
            DbPhoneService dbPhoneService,
            PhoneToDtoMapper phoneToDtoMapper) {
        this.dbPhoneService = dbPhoneService;
        this.phoneToDtoMapper = phoneToDtoMapper;
    }

    @PostMapping
    public ResponseEntity<PhoneDto> create(
            @RequestBody PhoneDto phoneDto
    ) {
        PhoneDto phoneToSave = phoneToDtoMapper.toDto(dbPhoneService.create(phoneDto));
        return ResponseEntity.ok().body(phoneToSave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneDto> getById(
            @PathVariable Long id
    ) {
        Phone phone = dbPhoneService.getById(id);
        return ResponseEntity.ok(phoneToDtoMapper.toDto(phone));
    }
}
