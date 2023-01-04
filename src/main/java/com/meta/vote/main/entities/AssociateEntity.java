package com.meta.vote.main.entities;


import com.meta.vote.main.utils.DateFormatterUtil;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ASSOCIATE")
public class AssociateEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(nullable = false, updatable = false)
    private final String createdAt = DateFormatterUtil.DATE_FORMAT.format(new Date());
    @Column(nullable = false)
    private boolean active = true;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(unique = true, length = 11, nullable = false, updatable = false)
    private String cpf;
    @Column(unique = true, nullable = false)
    private String email;

}
