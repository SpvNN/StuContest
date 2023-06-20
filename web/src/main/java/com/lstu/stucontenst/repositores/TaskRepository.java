package com.lstu.stucontenst.repositores;

import com.lstu.stucontenst.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
