package dstu.hacktesting.service.impl;

import dstu.hacktesting.dto.TaskDTO;
import dstu.hacktesting.entity.Student;
import dstu.hacktesting.entity.Task;
import dstu.hacktesting.repository.impl.StudentRepository;
import dstu.hacktesting.repository.impl.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;
    public Task create(TaskDTO taskDTO) {
        Student taskStudent = studentRepository.findById(taskDTO.getStudentId())
                .orElseThrow(RuntimeException::new);
        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .createDate(taskDTO.getCreateDate())
                .deadlineDate(taskDTO.getDeadlineDate())
                .priority(taskDTO.getPriority())
                .status(taskDTO.getStatus())
                .student(taskStudent)
                .build();
        return taskRepository.save(task);
    }
    public List<Task> readAll() {
        return taskRepository.findAll();
    }
    public Task update(Task task) {
        return taskRepository.save(task);
    }
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
