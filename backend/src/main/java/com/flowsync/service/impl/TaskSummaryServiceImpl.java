package com.flowsync.service.impl;

import com.flowsync.entity.TaskSummary;
import com.flowsync.mapper.TaskSummaryMapper;
import com.flowsync.service.TaskSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskSummaryServiceImpl implements TaskSummaryService {

    @Autowired
    private TaskSummaryMapper taskSummaryMapper;

    @Override
    public List<TaskSummary> listAll() {
        return taskSummaryMapper.selectAll();
    }

    @Override
    public void save(TaskSummary summary, Long currentUserId) {
        summary.setCreatedBy(currentUserId);
        taskSummaryMapper.insertSummary(summary);
    }
}
