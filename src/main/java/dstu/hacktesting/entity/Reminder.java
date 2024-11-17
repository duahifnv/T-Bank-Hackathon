package dstu.hacktesting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reminder_id")
    private Long id;
    private String description;
    @Column(name = "activation_time")
    private Timestamp activationTime;
    @Column(name = "task_id")
    private Long taskId;
    @Column(name = "student_id")
    private Long studentId;
}
