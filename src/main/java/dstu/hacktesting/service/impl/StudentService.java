package dstu.hacktesting.service.impl;

import dstu.hacktesting.dto.StudentDTO;
import dstu.hacktesting.entity.Student;
import dstu.hacktesting.repository.impl.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public Student create(StudentDTO studentDto) {
        Student student = Student.builder()
                .username(studentDto.getUsername())
                .passwordHash(studentDto.getPasswordHash())
                .email(studentDto.getEmail())
                .registerDate(studentDto.getRegisterDate())
                .lastLoginDate(studentDto.getLastLoginDate())
                .build();
        return studentRepository.save(student);
    }
    public List<Student> readAll() {
        return studentRepository.findAll();
    }
    public Student update(Student student) {
        return studentRepository.save(student);
    }
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
