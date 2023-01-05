package com.meta.vote.main.dtos.views;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssociateView {


    private String createdAt;
    private Integer id;
    private String name;
    private String cpf;
    private String email;

}