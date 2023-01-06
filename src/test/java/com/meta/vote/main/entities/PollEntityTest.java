package com.meta.vote.main.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PollEntityTest {


    @Test
    void whenGetCountSimAndGetCountNaoIsCalledItShouldReturnTheNumberOfVotesWithThisValue() {
        PollEntity entity = new PollEntity();

        List<VoteEntity> voteEntityList = new ArrayList<>() {{
            add(new VoteEntity());
            get(0).setVote("NÃO");
            add(new VoteEntity());
            get(1).setVote("NÃO");
            add(new VoteEntity());
            get(2).setVote("NÃO");
            add(new VoteEntity());
            get(3).setVote("NÃO");
            add(new VoteEntity());
            get(4).setVote("SIM");
        }};
        entity.getVoteEntityList().addAll(voteEntityList);

        assertThat(entity.getCountVotesNao(), is(equalTo(4)));
        assertThat(entity.getCountVotesSim(), is(equalTo(1)));
    }

}
