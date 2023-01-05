package com.meta.vote.main.configs.db_init;

import com.meta.vote.main.dtos.forms.AssociateForm;
import com.meta.vote.main.dtos.forms.PollForm;
import com.meta.vote.main.dtos.forms.ScheduleForm;
import com.meta.vote.main.dtos.forms.VoteForm;
import com.meta.vote.main.entities.AssociateEntity;
import com.meta.vote.main.entities.PollEntity;
import com.meta.vote.main.entities.ScheduleEntity;
import com.meta.vote.main.entities.VoteEntity;
import com.meta.vote.main.services.AssociateService;
import com.meta.vote.main.services.PollService;
import com.meta.vote.main.services.ScheduleService;
import com.meta.vote.main.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {


    @Autowired
    private AssociateService associateService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PollService pollService;
    @Autowired
    private VoteService voteService;


    public void instantiateTestDatabase() {
        AssociateForm associateForm1 = new AssociateForm();
        associateForm1.setCpf("20542839853");
        associateForm1.setName("Pedro");
        associateForm1.setEmail("example@example.com");
        AssociateEntity associateEntity1 = this.associateService.insert(associateForm1);
        AssociateForm associateForm2 = new AssociateForm();
        associateForm2.setCpf("42442839856");
        associateForm2.setName("Viego");
        associateForm2.setEmail("example2@example.com");
        AssociateEntity associateEntity2 = this.associateService.insert(associateForm2);
        AssociateForm associateForm3 = new AssociateForm();
        associateForm3.setCpf("42444849856");
        associateForm3.setName("Jorge");
        associateForm3.setEmail("example3@example.com");
        AssociateEntity associateEntity3 = this.associateService.insert(associateForm3);

        ScheduleForm scheduleForm1 = new ScheduleForm();
        scheduleForm1.setName("Ping-Pong");
        scheduleForm1.setDescription("Jogar ping pong com os compadres");
        ScheduleEntity scheduleEntity1 = this.scheduleService.insert(scheduleForm1);

        PollForm pollForm1 = new PollForm();
        pollForm1.setScheduleEntityId(scheduleEntity1.getId());
        PollEntity pollEntity1 = this.pollService.insert(pollForm1);
        PollForm pollForm2 = new PollForm();
        pollForm2.setDeadline(180);
        pollForm2.setScheduleEntityId(scheduleEntity1.getId());
        PollEntity pollEntity2 = this.pollService.insert(pollForm2);

        VoteForm voteForm1 = new VoteForm();
        voteForm1.setVote("SIM");
        voteForm1.setPollEntityId(pollEntity1.getId());
        voteForm1.setAssociateEntityId(associateEntity1.getId());
        VoteEntity voteEntity1 = this.voteService.insert(voteForm1);
        VoteForm voteForm2 = new VoteForm();
        voteForm2.setVote("NÃO");
        voteForm2.setPollEntityId(pollEntity1.getId());
        voteForm2.setAssociateEntityId(associateEntity2.getId());
        VoteEntity voteEntity2 = this.voteService.insert(voteForm2);
        VoteForm voteForm3 = new VoteForm();
        voteForm3.setVote("NÃO");
        voteForm3.setPollEntityId(pollEntity2.getId());
        voteForm3.setAssociateEntityId(associateEntity3.getId());
        VoteEntity voteEntity3 = this.voteService.insert(voteForm3);
    }

}
