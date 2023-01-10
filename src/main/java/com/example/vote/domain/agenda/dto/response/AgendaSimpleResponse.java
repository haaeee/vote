package com.example.vote.domain.agenda.dto.response;

import com.example.vote.domain.agenda.entity.Agenda;
import com.example.vote.domain.agenda.entity.AgendaStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AgendaSimpleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final AgendaStatus agendaStatus;

    public static AgendaSimpleResponse from(final Agenda agenda) {
        return new AgendaSimpleResponse(agenda.getId(), agenda.getTitle(), agenda.getContent(),
                agenda.getAgendaStatus());
    }
}
