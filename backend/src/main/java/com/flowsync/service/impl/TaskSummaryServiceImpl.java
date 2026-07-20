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
        // TODO[模块四]: 调用 taskSummaryMapper.selectAll() 返回全部总结
        return null;
    }

    @Override
    public void save(TaskSummary summary, Long currentUserId) {
        // TODO[模块四]: 设 createdBy = currentUserId，调用 taskSummaryMapper.insertSummary(summary)
    }
}
