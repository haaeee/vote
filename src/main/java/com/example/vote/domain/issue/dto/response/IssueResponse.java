package com.example.vote.domain.issue.dto.response;

import com.example.vote.domain.agenda.issue.entity.Issue;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IssueResponse {

    private final Long id;
    private final

    public static IssueResponse from(final Issue issue) {
        return null;
    }
}
