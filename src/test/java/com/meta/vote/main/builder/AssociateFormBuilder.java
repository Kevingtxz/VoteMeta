package com.meta.vote.main.builder;

import com.meta.vote.main.dto.form.AssociateForm;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
