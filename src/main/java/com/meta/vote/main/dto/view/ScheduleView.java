package com.meta.vote.main.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleView {


    private String createdAt;
    private Integer id;
    private String name;
    private String description;
    private String result;
    private int countVotesSim;
    private int countVotesNao;
    private List<PollView> pollViewList;

}