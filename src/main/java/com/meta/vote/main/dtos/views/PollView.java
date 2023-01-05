package com.meta.vote.main.dtos.views;

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
    private int countVotesSim;
    private int countVotesNao;
    private boolean finished;

}