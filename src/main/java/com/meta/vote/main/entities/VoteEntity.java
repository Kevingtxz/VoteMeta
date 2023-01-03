package com.meta.vote.main.entities;

import com.meta.vote.main.utils.DateFormatterUtil;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "VOTE")
public class VoteEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(nullable = false, updatable = false)
    private final String createdAt = DateFormatterUtil.DATE_FORMAT.format(new Date());
    @Column(nullable = false)
    private boolean active = true;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @ManyToOne
    @JoinColumn(name = "ASSOCIATE_ID", nullable = false)
    private AssociateEntity associateEntity;
    @ManyToOne
    @JoinColumn(name = "POLL_ID", nullable = false,  updatable = false)
    private PollEntity pollEntity;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private boolean vote;


    public String getVote() {
        return this.vote ? "SIM" : "NÃO";
    }
    public void setVote(String vote) {
        if (vote.equals("SIM") || vote.equals("NÃO")) {
            this.vote = vote == "SIM";
        }
    }
    public ScheduleEntity getScheduleEntity() {
        return this.pollEntity.getScheduleEntity();
    }

}
