package com.flowsync.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowsync.entity.TaskSummary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskSummaryMapper extends BaseMapper<TaskSummary> {

    @Select("SELECT * FROM task_summary ORDER BY id DESC")
    List<TaskSummary> selectAll();

    @Insert("INSERT INTO task_summary(project_id, task_id, summary_type, content, created_by) "
          + "VALUES(#{projectId}, #{taskId}, #{summaryType}, #{content}, #{createdBy})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSummary(TaskSummary s);
}
