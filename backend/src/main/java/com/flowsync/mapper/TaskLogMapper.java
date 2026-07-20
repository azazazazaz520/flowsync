package com.flowsync.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowsync.entity.TaskLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskLogMapper extends BaseMapper<TaskLog> {

    @Select("SELECT * FROM task_log ORDER BY id DESC")
    List<TaskLog> selectAll();

    @Select("SELECT * FROM task_log WHERE task_id = #{taskId} ORDER BY id DESC")
    List<TaskLog> selectByTaskId(@Param("taskId") Long taskId);

    @Insert("INSERT INTO task_log(task_id, progress_percent, content, operator_id) "
          + "VALUES(#{taskId}, #{progressPercent}, #{content}, #{operatorId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertLog(TaskLog log);
}
