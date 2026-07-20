package com.flowsync.service;

import com.flowsync.entity.TaskInfo;
import java.util.List;

public interface TaskService {

    /** 查询全部任务；若 projectId 不为 null 则按项目筛选 */
    List<TaskInfo> listAll(Long projectId);

    /** 新增或编辑任务。id 为空则新增（设 creator_id=currentUserId），有值则编辑 */
    void save(TaskInfo task, Long currentUserId);

    /** 删除任务 */
    void remove(Long id);
}
