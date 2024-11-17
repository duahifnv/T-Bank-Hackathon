package dstu.hacktesting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="student_id")
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password_hash")
    private String passwordHash;
    @Column(name="email")
    private String email;
    @Column(name="register_date")
    private Timestamp registerDate;
    @Column(name="last_login_date")
    private Timestamp lastLoginDate;
}
