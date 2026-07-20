/**
 * 数据访问层 — 各模块的 Mapper 接口清单。
 *
 * 【已存在】
 *   UserMapper.java — 用户表（参考模板，包含 @Select / @Insert / @Update / @Delete 注解 SQL 的完整写法）
 *
 * 【模块一】项目管理
 *   ProjectMapper.java → 参考 UserMapper.java
 *   表：project_info
 *
 * 【模块二】任务管理
 *   TaskMapper.java → 参考 UserMapper.java
 *   表：task_info
 *
 * 【模块三】进度跟踪 + 总结中心
 *   TaskLogMapper.java     → 参考 UserMapper.java   表：task_log
 *   TaskSummaryMapper.java → 参考 UserMapper.java   表：task_summary
 */
package com.flowsync.mapper;
