package br.com.smartservice.controllers;

import br.com.smartservice.dtos.UserDTO;
import br.com.smartservice.models.User;
import br.com.smartservice.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Cria um usuário")
    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @ApiOperation(value = "Retorna todos usuários.")
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.findAll();
    }

    @ApiOperation(value = "Busca um usuário por id.")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id){
        return userService.findById(id);
    }

    @ApiOperation(value = "Remove um usuário por id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID id){
        return userService.deleteById(id);
    }
    @ApiOperation(value = "Atualiza um usuário.")
    @PutMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody @Valid User user) {
        return userService.updateUser(user);
    }
}
