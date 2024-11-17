package dstu.hacktesting.controller.impl;

import dstu.hacktesting.dto.StudentDTO;
import dstu.hacktesting.entity.Student;
import dstu.hacktesting.service.impl.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public ResponseEntity<List<Student>> readAll() {
        return new ResponseEntity<>(studentService.readAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Student> create(@RequestBody StudentDTO studentDTO) {
        return new ResponseEntity<>(studentService.create(studentDTO), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Student> update(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.update(student), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        studentService.delete(id);
        return HttpStatus.OK;
    }
}
