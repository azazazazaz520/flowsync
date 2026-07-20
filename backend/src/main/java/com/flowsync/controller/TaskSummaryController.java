package com.flowsync.controller;

import com.flowsync.common.Result;
import com.flowsync.entity.TaskSummary;
import com.flowsync.service.TaskSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskSummaryController {

    @Autowired
    private TaskSummaryService taskSummaryService;

    // ==================== 模块四：总结中心 ====================

    /**
     * GET /api/summaries — 返回全部总结
     */
    @GetMapping("/api/summaries")
    public Result list() {
        // TODO[模块四]: 调用 taskSummaryService.listAll()，使用 Result.success().data("data", list) 返回
        return null;
    }

    /**
     * POST /api/summaries — 新增总结（仅有查询和新增，无编辑/删除）。
     * 在 Service 层设置 created_by = currentUserId。
     */
    @PostMapping("/api/summaries")
    public Result save(@RequestBody TaskSummary summary, Long currentUserId) {
        // TODO[模块四]: 调用 taskSummaryService.save(summary, currentUserId)，返回 Result.success()
        return null;
    }
}
