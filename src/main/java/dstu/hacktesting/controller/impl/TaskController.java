package dstu.hacktesting.controller.impl;

import dstu.hacktesting.dto.TaskDTO;
import dstu.hacktesting.entity.Task;
import dstu.hacktesting.service.impl.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @GetMapping
    public ResponseEntity<List<Task>> readAll() {
        return new ResponseEntity<>(taskService.readAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskDTO taskDTO) {
        return new ResponseEntity<>(taskService.create(taskDTO), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Task> update(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.update(task), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        taskService.delete(id);
        return HttpStatus.OK;
    }
}
