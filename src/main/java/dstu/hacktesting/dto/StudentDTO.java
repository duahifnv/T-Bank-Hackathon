package dstu.hacktesting.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class StudentDTO {
    private String username;
    private String passwordHash;
    private String email;
    private Timestamp registerDate;
    private Timestamp lastLoginDate;
}
