package com.flowsync.controller;

import com.flowsync.common.Result;
import com.flowsync.entity.TaskInfo;
import com.flowsync.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    // ==================== 模块二：任务管理 ====================

    /**
     * GET /api/tasks — 可选参数 projectId，传入则按项目筛选
     */
    @GetMapping("/api/tasks")
    public Result list(@RequestParam(required = false) Long projectId) {
        // TODO[模块二]: 调用 taskService.listAll(projectId)，使用 Result.success().data("data", list) 返回
        return null;
    }

    /**
     * POST /api/tasks — 新增或编辑。
     * 新增时在 Service 层设置 creator_id = currentUserId。
     */
    @PostMapping("/api/tasks")
    public Result save(@RequestBody TaskInfo task, Long currentUserId) {
        // TODO[模块二]: 调用 taskService.save(task, currentUserId)，返回 Result.success()
        return null;
    }

    /**
     * DELETE /api/tasks/{id} — 删除任务
     */
    @DeleteMapping("/api/tasks/{id}")
    public Result delete(@PathVariable Long id) {
        // TODO[模块二]: 调用 taskService.remove(id)，返回 Result.success()
        return null;
    }
}
