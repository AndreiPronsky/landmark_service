package org.pronsky.landmark_service.service;

import org.pronsky.landmark_service.service.dto.SettlementDto;

public interface SettlementService {
    SettlementDto create(SettlementDto settlementDto);
    SettlementDto update(SettlementDto settlementDto);
}
