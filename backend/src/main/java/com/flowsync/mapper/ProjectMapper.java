package com.flowsync.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flowsync.entity.ProjectInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<ProjectInfo> {

    @Select("SELECT * FROM project_info ORDER BY id DESC")
    List<ProjectInfo> selectAll();

    @Insert("INSERT INTO project_info(name, description, status, priority, owner_id, start_date, end_date) "
          + "VALUES(#{name}, #{description}, #{status}, #{priority}, #{ownerId}, #{startDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertProject(ProjectInfo p);

    @Update("UPDATE project_info SET name=#{name}, description=#{description}, status=#{status}, "
          + "priority=#{priority}, owner_id=#{ownerId}, start_date=#{startDate}, end_date=#{endDate} "
          + "WHERE id=#{id}")
    int updateProject(ProjectInfo p);

    @Delete("DELETE FROM project_info WHERE id=#{id}")
    int deleteById(@Param("id") Long id);
}
