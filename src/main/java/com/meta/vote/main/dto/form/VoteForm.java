package com.meta.vote.main.dto.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class VoteForm implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    private Integer associateEntityId;
    private Integer pollEntityId;
    @NotEmpty(message = "Mandatory completion")
    @Length(min = 3, max = 3, message = "The length must be 3 characters")
    private String vote;

}