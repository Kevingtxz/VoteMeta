package com.meta.vote.main.builders;

import com.meta.vote.main.dtos.forms.VoteForm;
import lombok.Builder;

@Builder
public class VoteFormBuilder {


    @Builder.Default
    private Integer id = 1;
    @Builder.Default
    private Integer associateEntityId = 1;
    @Builder.Default
    private Integer pollEntityId = 1;
    @Builder.Default
    private String vote = "SIM";


    public VoteForm toForm() {
        VoteForm form = new VoteForm();
        form.setId(this.id);
        form.setAssociateEntityId(this.associateEntityId);
        form.setPollEntityId(this.pollEntityId);
        form.setVote(this.vote);
        return form;
    }

}
