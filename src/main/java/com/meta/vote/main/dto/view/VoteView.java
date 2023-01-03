package com.meta.vote.main.dto.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VoteView {


    private String createdAt;
    private Integer id;
    private Integer associateEntityId;
    private Integer pollEntityId;
    private String vote;

}