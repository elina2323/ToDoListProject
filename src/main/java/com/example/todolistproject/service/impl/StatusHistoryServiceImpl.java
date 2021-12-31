package com.example.todolistproject.service.impl;

import com.example.todolistproject.dao.StatusHistoryRepo;
import com.example.todolistproject.mapper.StatusHistoryMapper;
import com.example.todolistproject.model.dto.StatusHistoryDto;
import com.example.todolistproject.model.entity.StatusHistory;
import com.example.todolistproject.service.StatusHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private StatusHistoryRepo statusHistoryRepo;

    @Override
    public StatusHistoryDto getLastStatus(Long taskId) {
        StatusHistory statusHistory = statusHistoryRepo.findByTaskIdAndEndDateIsNull(taskId);

        log.info("IN StatusHistoryServiceImpl getLastStatus - last status {} successfully got", taskId);

        return StatusHistoryMapper.INSTANCE.mapToStatusHistoryDto(statusHistory);
    }

    @Override
    public void saveStatusHistory(StatusHistoryDto statusHistoryDto) {
        StatusHistory statusHistory= StatusHistoryMapper.INSTANCE.mapToStatusHistory(statusHistoryDto);
        statusHistory = statusHistoryRepo.saveAndFlush(statusHistory);

        log.info("IN StatusHistoryServiceImpl save - status history {} successfully saved", statusHistoryDto);

        StatusHistoryMapper.INSTANCE.mapToStatusHistoryDto(statusHistory);
    }
}
