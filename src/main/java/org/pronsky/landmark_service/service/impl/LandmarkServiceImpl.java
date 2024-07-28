package org.pronsky.landmark_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pronsky.landmark_service.data.entities.Landmark;
import org.pronsky.landmark_service.data.repository.LandmarkRepository;
import org.pronsky.landmark_service.service.LandmarkService;
import org.pronsky.landmark_service.service.dto.LandmarkDto;
import org.pronsky.landmark_service.service.dto.SettlementDto;
import org.pronsky.landmark_service.service.mapper.EntityDtoMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {
    private final LandmarkRepository repository;
    private final EntityDtoMapper mapper;

    @Override
    public List<LandmarkDto> getAllByTypeSorted(String landmarkType, String sortingParam) {
        List<LandmarkDto> dtoList = repository
                .findAllByType(Landmark.LandmarkType.valueOf(landmarkType))
                .stream()
                .map(mapper::toDto)
                .sorted(Comparator.comparing(LandmarkDto::getName))
                .collect(Collectors.toList());
        if (sortingParam.equals("desc")) {
            Collections.reverse(dtoList);
        }
        return dtoList;
    }

    @Override
    public List<LandmarkDto> getAllBySettlement(SettlementDto settlement) {
        return repository.findAllBySettlementId(settlement.getId())
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public LandmarkDto create(LandmarkDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public LandmarkDto update(LandmarkDto dto) {
        LandmarkDto fetched = mapper.toDto(repository.findById(dto.getId())
                .orElseThrow(UnsupportedOperationException::new));
        if (allSameExceptDescription(dto, fetched)) {
            return mapper.toDto(repository.save(mapper.toEntity(dto)));
        }
        throw new UnsupportedOperationException("Forbidden to update anything except description");
    }

    @Override
    public void delete(LandmarkDto dto) {
        repository.delete(mapper.toEntity(dto));
    }

    private boolean allSameExceptDescription(LandmarkDto forUpdate, LandmarkDto fetched) {
        return (!forUpdate.getDescription().equals(fetched.getDescription()) &&
                forUpdate.getName().equals(fetched.getName()) &&
                forUpdate.getType().equals(fetched.getType()) &&
                forUpdate.getCreationYear().equals(fetched.getCreationYear()) &&
                forUpdate.getServices().equals(fetched.getServices()) &&
                forUpdate.getSettlement().equals(fetched.getSettlement()));
    }
}
