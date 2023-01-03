package com.meta.vote.main.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meta.vote.main.utils.DateFormatterUtil;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "SCHEDULE")
public class ScheduleEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(nullable = false, updatable = false)
    private final String createdAt = DateFormatterUtil.DATE_FORMAT.format(new Date());
    @Column(nullable = false)
    private boolean active = true;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(nullable = false)
    private String name;
    private String description;
    @ToString.Exclude
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "scheduleEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PollEntity> pollEntityList = new ArrayList<>();


    public String getResult() {
        int simCount = this.getVotesSim(), naoCount = this.getVotesNao();
        return "Opção : " + (simCount > naoCount ? "SIM" : simCount == naoCount ? "EMPATE" : "NÃO")
                + " a frente; " + simCount + " votos SIM; " + naoCount + " votos NÃO";
    }
    public int getVotesSim() {
        return this.pollEntityList.stream().mapToInt(obj -> obj.getVotesSim()).sum();
    }
    public int getVotesNao() {
        return this.pollEntityList.stream().mapToInt(obj -> obj.getVotesNao()).sum();
    }

}
