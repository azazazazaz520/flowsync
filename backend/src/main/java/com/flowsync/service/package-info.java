/**
 * 业务接口层 — 各模块的 Service 接口清单。
 *
 * 【已存在】
 *   UserService.java — 认证（登录 / 成员列表 / 修改密码）
 *
 * 【模块一】项目管理
 *   ProjectService.java → 参考 UserService.java
 *   方法：listAll() / save(ProjectInfo, Long currentUserId) / remove(Long id)
 *
 * 【模块二】任务管理
 *   TaskService.java → 参考 UserService.java
 *   方法：listAll() / listByProject(Long projectId) / save(TaskInfo, Long currentUserId) / remove(Long id)
 *
 * 【模块三】进度跟踪 + 总结中心
 *   TaskLogService.java → 参考 UserService.java
 *   方法：listAll() / listByTask(Long taskId) / save(TaskLog, Long currentUserId)
 *
 *   TaskSummaryService.java → 参考 UserService.java
 *   方法：listAll() / save(TaskSummary, Long currentUserId)
 */
package com.flowsync.service;
