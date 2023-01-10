package com.example.vote.domain.agenda.web;

import com.example.vote.domain.agenda.entity.AgendaStatus;
import com.example.vote.domain.agenda.service.AgendaService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agendas")
public class AgendaApiController {

    public final AgendaService agendaService;

    public AgendaApiController(final AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public ResponseEntity showPage(@RequestParam(required = false) final AgendaStatus agendaStatus, final Pageable pageable) {
        AgendaPageResponse response = agendaService.search(agendaStatus, pageable);
    }

}
