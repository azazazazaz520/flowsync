package com.flowsync.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowsync.entity.TaskInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper extends BaseMapper<TaskInfo> {

    @Select("SELECT * FROM task_info ORDER BY id DESC")
    List<TaskInfo> selectAll();

    @Select("SELECT * FROM task_info WHERE project_id = #{projectId} ORDER BY id DESC")
    List<TaskInfo> selectByProjectId(@Param("projectId") Long projectId);

    @Insert("INSERT INTO task_info(project_id, parent_id, title, description, assignee_id, creator_id, "
          + "status, priority, due_date, ai_suggestion) "
          + "VALUES(#{projectId}, #{parentId}, #{title}, #{description}, #{assigneeId}, #{creatorId}, "
          + "#{status}, #{priority}, #{dueDate}, #{aiSuggestion})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertTask(TaskInfo t);

    @Update("UPDATE task_info SET project_id=#{projectId}, parent_id=#{parentId}, title=#{title}, "
          + "description=#{description}, assignee_id=#{assigneeId}, creator_id=#{creatorId}, "
          + "status=#{status}, priority=#{priority}, due_date=#{dueDate}, ai_suggestion=#{aiSuggestion} "
          + "WHERE id=#{id}")
    int updateTask(TaskInfo t);

    @Delete("DELETE FROM task_info WHERE id=#{id}")
    int deleteById(@Param("id") Long id);
}
