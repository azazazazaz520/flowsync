package com.flowsync.service.impl;

import com.flowsync.entity.TaskInfo;
import com.flowsync.mapper.TaskMapper;
import com.flowsync.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskInfo> listAll(Long projectId) {
        // TODO[模块二]: 如果 projectId 不为 null，调用 taskMapper.selectByProjectId(projectId)
        //              否则调用 taskMapper.selectAll()
        return null;
    }

    @Override
    public void save(TaskInfo task, Long currentUserId) {
        // TODO[模块二]: 判断 task.getId() 是否为 null（新增 or 编辑）
        //   新增时：设 creatorId = currentUserId，调用 taskMapper.insertTask(task)
        //   编辑时：调用 taskMapper.updateTask(task)
    }

    @Override
    public void remove(Long id) {
        // TODO[模块二]: 调用 taskMapper.deleteById(id) 删除任务
    }
}
