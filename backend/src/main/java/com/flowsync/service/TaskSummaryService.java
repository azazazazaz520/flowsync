package com.flowsync.service;

import com.flowsync.entity.TaskSummary;
import java.util.List;

public interface TaskSummaryService {

    /** 查询全部总结 */
    List<TaskSummary> listAll();

    /** 新增总结，自动设 created_by = currentUserId */
    void save(TaskSummary summary, Long currentUserId);
}
