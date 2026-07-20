package com.flowsync.service.impl;

import com.flowsync.entity.TaskLog;
import com.flowsync.mapper.TaskLogMapper;
import com.flowsync.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLogServiceImpl implements TaskLogService {

    @Autowired
    private TaskLogMapper taskLogMapper;

    @Override
    public List<TaskLog> listAll(Long taskId) {
        // TODO[模块三]: 如果 taskId 不为 null，调用 taskLogMapper.selectByTaskId(taskId)
        //              否则调用 taskLogMapper.selectAll()
        return null;
    }

    @Override
    public void save(TaskLog log, Long currentUserId) {
        // TODO[模块三]: 设 operatorId = currentUserId，调用 taskLogMapper.insertLog(log)
    }
}
