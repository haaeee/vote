package com.example.vote.domain.agenda.dto.response;

import com.example.vote.domain.agenda.entity.Agenda;
import com.example.vote.domain.agenda.entity.AgendaStatus;
import com.example.vote.domain.agenda.issue.entity.Issue;
import com.example.vote.domain.issue.dto.response.IssueResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AgendaResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final AgendaStatus agendaStatus;

    private final IssueSimpleResponse issueResponse;

    private final Long administratorId;

    public static AgendaResponse from(Agenda agenda, Issue issue) {
        return new AgendaResponse(agenda.getId(), agenda.getTitle(), agenda.getContent(), agenda.getAgendaStatus(),
                IssueResponse.from(issue), agenda.getMember().getId());
    }
}
