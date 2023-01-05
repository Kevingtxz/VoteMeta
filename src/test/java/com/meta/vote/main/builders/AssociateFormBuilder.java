package com.meta.vote.main.builders;

import com.meta.vote.main.dtos.forms.AssociateForm;
import lombok.Builder;

@Builder
public class AssociateFormBuilder {


    @Builder.Default
    private Integer id = 1;
    @Builder.Default
    private String name = "Test1Name";
    @Builder.Default
    private String email = "text1email@example.com";
    @Builder.Default
    private String cpf = "10020030040";


    public AssociateForm toForm() {
        AssociateForm form = new AssociateForm();
        form.setId(this.id);
        form.setName(this.name);
        form.setEmail(this.email);
        form.setCpf(this.cpf);
        return form;
    }

}
