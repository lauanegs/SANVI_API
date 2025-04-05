package com.sanvi.sanvi_api.controller;

import com.sanvi.sanvi_api.domain.User;
import com.sanvi.sanvi_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
     * @param username Usuário
     * @param password Senha
     * @return Usuário Logado
     * @throws RuntimeException Usuário não encontrado ou senha incorreta
     */
    @PostMapping("login")
    public User login(@RequestBody String username, String password) {
        return userService.login(username, password);
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
