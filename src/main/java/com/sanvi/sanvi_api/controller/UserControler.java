package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.controller.dto.LoginRequest;
import com.sanvi.sanvi_api.domain.User;
import com.sanvi.sanvi_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:1420")
@RequestMapping("/user")
public class UserControler {

    @Autowired
    private UserService userService;

    /**
     * @return Lista de todos usuários
     */
    @GetMapping
    public List<User> list() {
        return userService.list();
    }

    /**
     * Realiza login
     * @param loginRequest { username: string, password: string}
     * @return Usuário Logado
     * @throws RuntimeException Usuário não encontrado ou senha incorreta
     */
    @CrossOrigin(origins = "http://localhost:1420")
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(user);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping("create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int Id){
        userService.delete(Id); //Testar if not found
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}
