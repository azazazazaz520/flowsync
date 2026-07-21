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
        return projectMapper.selectAll();
    }

    @Override
    public void save(ProjectInfo project, Long currentUserId) {
        if (project.getId() == null) {
            project.setOwnerId(currentUserId);
            projectMapper.insertProject(project);
        } else {
            projectMapper.updateProject(project);
        }
    }

    @Override
    public void remove(Long id) {
        projectMapper.deleteById(id);
    }
}
