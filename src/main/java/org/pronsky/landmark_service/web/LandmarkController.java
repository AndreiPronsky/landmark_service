package org.pronsky.landmark_service.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/landmarks")
public class LandmarkController {
    private final LandmarkService service;

    @GetMapping("/by_settlement")
    public ResponseEntity<List<LandmarkDto>> getAllBySettlement(@RequestBody SettlementDto settlement) {
        List<LandmarkDto> dtoList = service.getAllBySettlement(settlement);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LandmarkDto>> getAll(@RequestParam String sortingParam, @RequestParam String type) {
        List<LandmarkDto> dtoList = service.getAllByTypeSorted(type, sortingParam);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LandmarkDto> doPost(@RequestBody LandmarkDto landmark) {
        LandmarkDto created = service.create(landmark);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping
    public ResponseEntity<LandmarkDto> doPatch(@RequestBody LandmarkDto landmark) {
        LandmarkDto updated = service.update(landmark);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
    }

    @DeleteMapping
    public ResponseEntity<LandmarkDto> doDelete(@RequestBody LandmarkDto landmark) {
        service.delete(landmark);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
