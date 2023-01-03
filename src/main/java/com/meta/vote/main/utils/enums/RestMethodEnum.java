package com.meta.vote.main.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestMethodEnum {


    CREATE("CREATE"),
    READ( "READ"),
    UPDATE( "UPDATE"),
    DELETE("DELETE"),
    PATCH("PATCH");


    private String text;

}