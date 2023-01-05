package com.meta.vote.main.dtos.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PollForm implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;
    private Integer deadline = 60;
    private Integer scheduleEntityId;

}