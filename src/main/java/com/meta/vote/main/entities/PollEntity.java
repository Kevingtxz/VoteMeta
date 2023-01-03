package com.meta.vote.main.entities;


import com.meta.vote.main.utils.DateFormatterUtil;
import lombok.*;

import javax.persistence.*;
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
@Table(name = "POLL")
public class PollEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(nullable = false, updatable = false)
    private final String createdAt = DateFormatterUtil.DATE_FORMAT.format(new Date());
    @Column(nullable = false)
    private boolean active = true;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private Long deadline;
    @ManyToOne
    @JoinColumn(name = "SCHEDULE_ID", nullable = false, updatable = false)
    private ScheduleEntity scheduleEntity;
    @ToString.Exclude
    @OneToMany(mappedBy = "pollEntity", cascade = CascadeType.ALL)
    private List<VoteEntity> voteEntityList = new ArrayList<>();


    public int getVotes(String text) {
        return (int) this.getVoteEntityList()
                .stream()
                .filter(obj -> obj.getVote().equals(text)).count();

    }
    public int getVotesSim() {
        return this.getVotes("SIM");
    }
    public int getVotesNao() {
        return this.getVotes("NÃO");
    }
    public String getDeadline() {
        return DateFormatterUtil.DATE_FORMAT.format(this.deadline);
    }
    public boolean isFinished() {
        return new Date().getTime() > this.deadline;
    }
    public void setDeadline(int deadline) {
        this.deadline =  new Date().getTime() + deadline * 1000;
    }

}
