package com.sanvi.sanvi_api.service;

import com.sanvi.sanvi_api.domain.User;
import com.sanvi.sanvi_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        validatePassword(user.getPassword());
        //Criptografar senha
        return userRepository.save(user);
    }

    public User login(String username, String password){
        User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        //Criptografar senha
        if(user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Senha incorreta");
        }
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public List<User> list(){
        return userRepository.findAll();
    }

    private void validatePassword(String senha) {
        if (senha == null || senha.length() <= 8) {
            throw new IllegalArgumentException("A senha deve ter mais que 8 caracteres.");
        }

        if (!Pattern.compile("[A-Z]").matcher(senha).find()) {
            throw new IllegalArgumentException("A senha deve conter pelo menos uma letra maiúscula.");
        }

        if (!Pattern.compile("[^a-zA-Z0-9]").matcher(senha).find()) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um caractere especial.");
        }
    }
}
