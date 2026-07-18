package com.flowsync.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("project_info")
public class ProjectInfo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String status;
    private String priority;
    private Long ownerId;
    private LocalDate startDate;
    private LocalDate endDate;

    @TableField(value = "create_time")
    private LocalDateTime createTime;
}
