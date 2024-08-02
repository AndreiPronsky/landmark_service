package org.pronsky.landmark_service.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkFullDto;
import org.pronsky.landmark_service.service.dto.LandmarkTrimmedDto;
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
    public ResponseEntity<List<LandmarkFullDto>> getAllBySettlement(@RequestParam String settlement) {
        List<LandmarkFullDto> dtoList = service.getAllBySettlement(settlement);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LandmarkFullDto>> getAll(@RequestParam String sortingParam, @RequestParam String type) {
        List<LandmarkFullDto> dtoList = service.getAllByTypeSorted(type, sortingParam);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LandmarkFullDto> doPost(@RequestBody LandmarkTrimmedDto landmark) {
        LandmarkFullDto created = service.create(landmark);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping
    public ResponseEntity<LandmarkFullDto> doPatch(@RequestBody LandmarkTrimmedDto landmark) {
        LandmarkFullDto updated = service.update(landmark);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
    }

    @DeleteMapping
    public ResponseEntity<LandmarkFullDto> doDelete(@RequestParam Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
