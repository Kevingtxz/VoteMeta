package com.meta.vote.main.dto.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleForm implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    @Length(min = 5, max = 200, message = "The length must be between 5 and 200 characters")
    @NotEmpty(message = "Mandatory completion")
    @NotBlank(message = "Mandatory completion")
    private String name;
    private String description;

}