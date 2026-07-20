/**
 * 接口层 — 各模块的 Controller 文件清单。
 *
 * 【已存在】
 *   AuthController.java   — 认证（登录 / 成员列表 / 修改密码）
 *
 * 【模块一】项目管理
 *   ProjectController.java → 参考 AuthController.java
 *   接口：
 *     GET    /api/projects         返回全部项目
 *     POST   /api/projects         新增/编辑（新增时 owner_id = currentUserId）
 *     DELETE /api/projects/{id}    删除
 *
 * 【模块二】任务管理
 *   TaskController.java → 参考 AuthController.java
 *   接口：
 *     GET    /api/tasks            返回任务列表（可选参数 projectId）
 *     POST   /api/tasks            新增/编辑（新增时 creator_id = currentUserId）
 *     DELETE /api/tasks/{id}       删除
 *
 * 【模块三】进度跟踪 + 总结中心
 *   TaskLogController.java → 参考 AuthController.java
 *   接口：
 *     GET    /api/task-logs        返回进度列表（可选参数 taskId）
 *     POST   /api/task-logs        新增进度
 *
 *   TaskSummaryController.java → 参考 AuthController.java
 *   接口：
 *     GET    /api/summaries        返回全部总结
 *     POST   /api/summaries        新增总结
 *
 * 【附加】
 *   AiController.java          — AI 任务拆解（后期实现）
 *   OverviewController.java    — 总览统计（后期实现）
 */
package com.flowsync.controller;
