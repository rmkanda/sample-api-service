package com.sample.UserManager;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id)
            .map(user -> {
                user.setUserName(newUser.getUserName());
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                return repository.save(user);
            })
            .orElseGet(() -> {
                return repository.save(newUser);
            });
    }

    @PostMapping("/users/login")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "401", description = "Login Failure"),
        @ApiResponse(responseCode = "200", description = "Login Success"),
    })
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        List<User> users = repository.findAll();
        for (User other : users) {
            if (other.getUserName().equals(user.getUserName()) && other.getPassword().equals(user.getPassword())) {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
