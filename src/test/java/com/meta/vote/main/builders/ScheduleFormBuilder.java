package com.meta.vote.main.builders;

import com.meta.vote.main.dtos.forms.ScheduleForm;
import lombok.Builder;

@Builder
public class ScheduleFormBuilder {


    @Builder.Default
    private Integer id = 1;
    @Builder.Default
    private String name = "Test1Name";
    @Builder.Default
    private String description = "text1description";


    public ScheduleForm toForm() {
        ScheduleForm form = new ScheduleForm();
        form.setId(this.id);
        form.setName(this.name);
        form.setDescription(description);
        return form;
    }

}
