package com.meta.vote.main.dtos.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
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