package com.example.todolistproject.service;

import com.example.todolistproject.model.dto.StatusHistoryDto;

public interface StatusHistoryService {

    StatusHistoryDto getLastStatus(Long taskId);

    void saveStatusHistory(StatusHistoryDto statusHistoryDto);
}
