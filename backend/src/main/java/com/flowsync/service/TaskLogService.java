package com.flowsync.service;

import com.flowsync.entity.TaskLog;
import java.util.List;

public interface TaskLogService {

    /** 查询全部进度记录；若 taskId 不为 null 则按任务筛选 */
    List<TaskLog> listAll(Long taskId);

    /** 新增进度记录，自动设 operator_id = currentUserId */
    void save(TaskLog log, Long currentUserId);
}
