package com.flowsync.service.impl;

import com.flowsync.entity.ProjectInfo;
import com.flowsync.mapper.ProjectMapper;
import com.flowsync.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<ProjectInfo> listAll() {
        // TODO[模块一]: 调用 projectMapper.selectAll() 返回全部项目
        return null;
    }

    @Override
    public void save(ProjectInfo project, Long currentUserId) {
        // TODO[模块一]: 判断 project.getId() 是否为 null（新增 or 编辑）
        //   新增时：设 ownerId = currentUserId，调用 projectMapper.insertProject(project)
        //   编辑时：调用 projectMapper.updateProject(project)
    }

    @Override
    public void remove(Long id) {
        // TODO[模块一]: 调用 projectMapper.deleteById(id) 删除项目
    }
}
