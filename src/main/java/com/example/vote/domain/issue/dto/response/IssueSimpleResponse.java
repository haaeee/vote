package com.example.vote.domain.issue.dto.response;

import com.example.vote.domain.agenda.issue.entity.Issue;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IssueSimpleResponse {

    private final Long id;
    private final

    public static IssueSimpleResponse from(final Issue issue) {
        return null;
    }
}
