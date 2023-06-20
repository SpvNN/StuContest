package com.lstu.stucontenst.services;

import com.lstu.stucontenst.AuthenticationProvider;
import com.lstu.stucontenst.entities.TaskEntity;
import com.lstu.stucontenst.entities.UserEntity;
import com.lstu.stucontenst.repositores.TaskRepository;
import com.lstu.stucontenst.request.SignupRequest;
import com.lstu.stucontenst.repositores.UserRepository;
import com.lstu.stucontenst.request.TaskPartRequest;
import com.lstu.stucontenst.request.TaskRequest;
import com.lstu.stucontenst.request.TasksRequest;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class RestService {
    final protected AuthenticationProvider authProvider;
    final protected TaskRepository taskRepository;
    final protected UserRepository userRepository;


    public RestService(
            UserRepository userRepository,
            TaskRepository taskRepository
    ) {
        this.authProvider = new AuthenticationProvider();
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @CrossOrigin
    @PostMapping("/user")
    public ResponseEntity<?> signupNewUser(
            @RequestBody SignupRequest body
            ) {
        if (this.userRepository.findUserByEmailAndPassword(body.email, body.password).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        UserEntity entity = new UserEntity(body.email, body.password, List.of());
        this.userRepository.save(entity);

        String token = this.authProvider.createKey(body.email, body.password).get();
        HttpCookie cookie = ResponseCookie.from("stu-contest", token)
                .path("/")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }


    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(
            @RequestBody SignupRequest body
    ) {
        Optional<UserEntity> user = this.userRepository.findUserByEmailAndPassword(body.email, body.password);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String token = this.authProvider.createKey(body.email, body.password).get();

        return ResponseEntity
                .ok()
                .header("", token)
                .build();
    }

    @PostMapping("/user/tasks")
    public ResponseEntity<?> postUserTasks(
            @RequestBody TasksRequest request
    ) {
        Optional<UserEntity> optionalUser = this.userRepository.findUserByEmail(request.email);
        LoggerFactory.getLogger(RestService.class).info("User is " + request.toString() + " opt " + optionalUser.get().getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalUser.get().getTasks());
    }

    @CrossOrigin
    @DeleteMapping("/user/task")
    public ResponseEntity postUserTask(
            @RequestBody TaskRequest body
       ) {
        Optional<UserEntity> user = userRepository.findUserByEmail(body.email);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskEntity task_o = null;
        for (TaskEntity task :
                user.get().getTasks()) {
            if (task.getName() == body.task.name) {
                task_o = task;
                break;
            }
        }

        if (task_o == null) {
            return ResponseEntity.notFound().build();
        }

        this.taskRepository.delete(task_o);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/user/task")
    public ResponseEntity updateUserTask(
            @RequestBody TaskRequest body
    ) {
        Optional<UserEntity> user = userRepository.findUserByEmail(body.email);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskEntity task_o = null;
        for (TaskEntity task :
                user.get().getTasks()) {
            if (task.getName() == body.task.name) {
                task_o = task;
                break;
            }
        }

        if (task_o == null) {
            return ResponseEntity.notFound().build();
        }

        this.taskRepository.save(task_o);

        return ResponseEntity.ok().build();
    }
}
