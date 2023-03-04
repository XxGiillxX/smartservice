package br.com.smartservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Size(max = 130, message = "Máximo de 130 caracteres.")
    @NotBlank(message = "Nome é obrigatório.")
    private String name;

    @NotBlank(message = "CPF é obrigatório.")
    @CPF(message = "CPF inválido")
    @Size(max = 11, min = 11, message = "O CPF deve conter apenas números.")
    private String registrationNumber;
    @NotBlank(message = "O telefone é obrigatório.")
    @Size(max = 11, min = 10, message = "O telefone deve conter apenas números.")
    private String phoneNumber;
}
