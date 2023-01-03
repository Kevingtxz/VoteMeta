package com.meta.vote.main.builder;

import com.meta.vote.main.dto.form.PollForm;
import lombok.Builder;

@Builder
public class PollFormBuilder {


    @Builder.Default
    private Integer id = 1;
    @Builder.Default
    private Integer deadline = 60;
    @Builder.Default
    private Integer scheduleEntityId = 1;


    public PollForm toForm() {
        PollForm form = new PollForm();
        form.setId(this.id);
        form.setDeadline(this.deadline);
        form.setScheduleEntityId(this.scheduleEntityId);
        return form;
    }

}
