package com.example.vote.domain.issue.entity;


import com.example.vote.domain.agenda.entity.Agenda;
import com.example.vote.domain.vote.entity.Vote;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "issue")
public class Issue {

    private static final int VALID_VOTE_COUNT = 10;
    private static final int NO_LIMITED_ISSUE_COUNT_NUMBER_FLAG = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @Enumerated(EnumType.STRING)
    private IssueType issueType;

    private int votingCount;

    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "issue", fetch = FetchType.LAZY)
    private Set<Vote> votes = new LinkedHashSet<>();

    protected Issue() {
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Issue(Long id, Agenda agenda, IssueType issueType, int votingCount) {
        this.id = id;
        this.agenda = agenda;
        this.issueType = issueType;
        this.votingCount = votingCount;
    }

    public static Issue createLimitedIssue(Agenda agenda) {
        return Issue.builder()
                .agenda(agenda)
                .issueType(IssueType.LIMITED)
                .votingCount(VALID_VOTE_COUNT)
                .build();
    }

    public static Issue createNoLimitedIssue(Agenda agenda) {
        return Issue.builder()
                .agenda(agenda)
                .issueType(IssueType.NO_LIMITED)
                .votingCount(NO_LIMITED_ISSUE_COUNT_NUMBER_FLAG)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Issue issue = (Issue) o;
        return getId().equals(issue.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
