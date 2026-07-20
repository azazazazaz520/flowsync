package com.flowsync.controller;

import com.flowsync.common.Result;
import com.flowsync.entity.ProjectInfo;
import com.flowsync.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // ==================== 模块一：项目管理 ====================

    /**
     * GET /api/projects — 返回全部项目，按 id 倒序
     */
    @GetMapping("/api/projects")
    public Result list() {
        // TODO[模块一]: 调用 projectService.listAll()，使用 Result.success().data("data", list) 返回
        return null;
    }

    /**
     * POST /api/projects — id 为空则新增，有值则编辑。
     * 新增时在 Service 层设置 owner_id = currentUserId。
     * currentUserId 由前端 axios 拦截器自动注入为请求参数。
     */
    @PostMapping("/api/projects")
    public Result save(@RequestBody ProjectInfo project, Long currentUserId) {
        // TODO[模块一]: 调用 projectService.save(project, currentUserId)，返回 Result.success()
        return null;
    }

    /**
     * DELETE /api/projects/{id} — 删除项目
     */
    @DeleteMapping("/api/projects/{id}")
    public Result delete(@PathVariable Long id) {
        // TODO[模块一]: 调用 projectService.remove(id)，返回 Result.success()
        return null;
    }
}
