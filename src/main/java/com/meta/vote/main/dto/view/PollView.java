package com.meta.vote.main.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PollView {


    private String createdAt;
    private Integer id;
    private String deadline;
    private Integer scheduleEntityId;
    private int votesSim;
    private int votesNao;
    private boolean finished;

}