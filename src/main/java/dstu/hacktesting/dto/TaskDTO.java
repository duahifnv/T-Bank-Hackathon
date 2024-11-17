package dstu.hacktesting.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private Timestamp createDate;
    private Timestamp deadlineDate;
    private String priority;
    private String status;
    private Long studentId;
}
