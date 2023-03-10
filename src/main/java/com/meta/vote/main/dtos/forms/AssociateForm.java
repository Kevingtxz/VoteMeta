package com.meta.vote.main.dtos.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AssociateForm implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    @NotEmpty(message = "Mandatory completion")
    @Length(min = 5, max = 200, message = "The length must be between 5 and 200 characters")
    private String name;
    @NotEmpty(message = "Mandatory completion")
    @Email(message = "Invalid email")
    private String email;
    @NotEmpty(message = "Mandatory completion")
    @CPF(message = "Invalid cpf")
    private String cpf;

}