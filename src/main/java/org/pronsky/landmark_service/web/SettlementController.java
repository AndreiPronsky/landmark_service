package org.pronsky.landmark_service.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.landmark_service.service.SettlementService;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlements")
public class SettlementController {
    private final SettlementService service;

    @PostMapping
    public ResponseEntity<SettlementDto> doPost(@RequestBody SettlementDto settlement) {
        SettlementDto created = service.create(settlement);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping
    public ResponseEntity<SettlementDto> doPatch(@RequestBody SettlementDto settlement) {
        SettlementDto updated = service.update(settlement);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
    }
}
