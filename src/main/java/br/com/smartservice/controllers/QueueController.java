package br.com.smartservice.controllers;

import br.com.smartservice.daos.QueueDAO;
import br.com.smartservice.models.Queue;
import br.com.smartservice.models.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/queue")
public class QueueController {

    final QueueDAO queueDAO;

    public QueueController(QueueDAO queueDAO) {
        this.queueDAO = queueDAO;
    }

    @ApiOperation(value = "Retorna toda a fila.")
    @GetMapping("/all")
    public ResponseEntity<List<Queue>> queueAll(){
        return ResponseEntity.status(HttpStatus.OK).body(queueDAO.findAll());
    }
}
