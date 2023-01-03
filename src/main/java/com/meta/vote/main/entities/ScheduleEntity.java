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
    @Column(nullable = true)
    private String description;
    @ToString.Exclude
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "scheduleEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PollEntity> pollEntityList = new ArrayList<>();


    public String getResult() {
        int simCount = this.pollEntityList.stream().mapToInt(obj -> obj.getVotesSim()).sum(),
                naoCount = this.pollEntityList.stream().mapToInt(obj -> obj.getVotesNao()).sum();
        if (simCount > naoCount) {
            return "A opção SIM está vencendo com "
                    + simCount + " votos contra "
                    + naoCount + " da opção NÃO.";
        } else if (naoCount > simCount) {
            return "A opção NÃO está vencendo com "
                    + naoCount + " votos contra "
                    + simCount + " da opção SIM.";
        } else {
            return "A votação está empatada com "
                    + simCount + " votos SIM e "
                    + naoCount +" votos NÃO.";
        }
    }

}
