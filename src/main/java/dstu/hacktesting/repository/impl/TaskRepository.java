package dstu.hacktesting.repository.impl;

import dstu.hacktesting.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
