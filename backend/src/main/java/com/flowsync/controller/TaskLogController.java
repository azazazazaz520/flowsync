package com.flowsync.controller;

import com.flowsync.common.Result;
import com.flowsync.entity.TaskLog;
import com.flowsync.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskLogController {

    @Autowired
    private TaskLogService taskLogService;

    // ==================== 模块三：进度跟踪 ====================

    /**
     * GET /api/task-logs — 可选参数 taskId，传入则按任务筛选
     */
    @GetMapping("/api/task-logs")
    public Result list(@RequestParam(required = false) Long taskId) {
        // TODO[模块三]: 调用 taskLogService.listAll(taskId)，使用 Result.success().data("data", list) 返回
        return null;
    }

    /**
     * POST /api/task-logs — 新增进度记录（仅有新增，无编辑/删除）。
     * 在 Service 层设置 operator_id = currentUserId。
     */
    @PostMapping("/api/task-logs")
    public Result save(@RequestBody TaskLog log, Long currentUserId) {
        // TODO[模块三]: 调用 taskLogService.save(log, currentUserId)，返回 Result.success()
        return null;
    }
}
