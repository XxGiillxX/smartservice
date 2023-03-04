package br.com.smartservice.services;

import br.com.smartservice.daos.QueueDAO;
import br.com.smartservice.daos.UserDAO;
import br.com.smartservice.dtos.UserDTO;
import br.com.smartservice.models.Queue;
import br.com.smartservice.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final UserDAO userDAO;
    final QueueDAO queueDAO;

    public UserService(UserDAO userDAO, QueueDAO queueDAO) {
        this.userDAO = userDAO;
        this.queueDAO = queueDAO;
    }

    @Transactional
    public ResponseEntity<Object> createUser(@NotNull UserDTO userDTO) {
        List<User> users = userDAO.findAll();
        if (users.size() <= 1000) {
            if (!userDAO.existsUserByRegistrationNumber(userDTO.getRegistrationNumber())) {
                var user = resolveAndReturnUser(userDTO);
                User userSave = userDAO.save(user);
                this.putUserInQueue(userSave);
                return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CPF already registered in use to system!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Maximum users and passwords created.");
        }
    }

    private void putUserInQueue(User userSave) {
        var queue = new Queue();
        queue.setUser(userSave);
        queue.setDhInput(LocalDateTime.now());
        queueDAO.save(queue);
    }

    private @NotNull User resolveAndReturnUser(UserDTO userDTO) {
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setName(user.getName().toUpperCase());
        return user;
    }

    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userDAO.findAll());
    }

    public ResponseEntity<Object> findById(UUID id) {
        Optional<User> optionalUser = userDAO.findById(id);
        return this.resolveReturnFind(optionalUser);
    }

    private ResponseEntity<Object> resolveReturnFind(@NotNull Optional<User> optionalUser) {
        return optionalUser.<ResponseEntity<Object>>map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User note found."));
    }
    @Transactional
    public ResponseEntity<Object> deleteById(UUID id) {
        Optional<User> optionalUser = userDAO.findById(id);
        if (Objects.nonNull(optionalUser.get())) {
            queueDAO.deleteByUser(optionalUser.get());
            userDAO.delete(optionalUser.get());
            return ResponseEntity.status(HttpStatus.OK).body("User removed.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User note found.");
        }
    }
    @Transactional
    public ResponseEntity<Object> updateUser(User user) {
        Optional<User> optionalUser = userDAO.findById(user.getId());
        if(Objects.nonNull(optionalUser.get())){
            var userOld = optionalUser.get();
            userOld.setName(user.getName().toUpperCase());
            userOld.setPhoneNumber(user.getPhoneNumber());
            userOld.setRegistrationNumber(user.getRegistrationNumber());
            userDAO.save(userOld);
            return ResponseEntity.status(HttpStatus.OK).body("User updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User note found.");
        }
    }
}
