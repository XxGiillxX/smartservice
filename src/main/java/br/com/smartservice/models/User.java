package br.com.smartservice.models;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@DynamicUpdate
@Table(name = "pessoa")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pessoa", nullable = false)
    private UUID id;
    @Column(name = "nome", nullable = false, length = 130)
    @Size(max = 130, message = "Máximo de 130 caracteres.")
    @NotBlank(message = "Nome é obrigatório.")
    private String name;
    @NotBlank(message = "CPF é obrigatório.")
    @CPF(message = "CPF inválido")
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String registrationNumber;
    @Column(name = "telefone", nullable = false, unique = true, length = 11)
    @NotBlank(message = "O telefone é obrigatório.")
    @Size(max = 11, min = 10, message = "O telefone deve conter apenas números.")
    private String phoneNumber;
}
