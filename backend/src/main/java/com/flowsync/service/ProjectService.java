package com.flowsync.service;

import com.flowsync.entity.ProjectInfo;
import java.util.List;

public interface ProjectService {

    /** 查询全部项目，按 id 倒序 */
    List<ProjectInfo> listAll();

    /** 新增或编辑项目。id 为空则新增（设 owner_id=currentUserId），有值则编辑 */
    void save(ProjectInfo project, Long currentUserId);

    /** 删除项目 */
    void remove(Long id);
}
