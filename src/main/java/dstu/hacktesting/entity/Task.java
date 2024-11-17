package dstu.hacktesting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table (name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "task_id")
    private Long id;
    private String title;
    private String description;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "deadline_date")
    private Timestamp deadlineDate;
    private String priority;
    private String status;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
