package com.voluntrack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private Long givenBy;

    private String userRole;

    @Column(nullable = false)
    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    public Feedback() {}

    public Feedback(Long id, Long eventId, Long givenBy, String userRole, Integer rating, String comment) {
        this.id = id;
        this.eventId = eventId;
        this.givenBy = givenBy;
        this.userRole = userRole;
        this.rating = rating;
        this.comment = comment;
    }

    public static FeedbackBuilder builder() {
        return new FeedbackBuilder();
    }

    public static class FeedbackBuilder {
        private Long id;
        private Long eventId;
        private Long givenBy;
        private String userRole;
        private Integer rating;
        private String comment;

        public FeedbackBuilder id(Long id) { this.id = id; return this; }
        public FeedbackBuilder eventId(Long eventId) { this.eventId = eventId; return this; }
        public FeedbackBuilder givenBy(Long givenBy) { this.givenBy = givenBy; return this; }
        public FeedbackBuilder userRole(String userRole) { this.userRole = userRole; return this; }
        public FeedbackBuilder rating(Integer rating) { this.rating = rating; return this; }
        public FeedbackBuilder comment(String comment) { this.comment = comment; return this; }

        public Feedback build() {
            return new Feedback(id, eventId, givenBy, userRole, rating, comment);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public Long getGivenBy() { return givenBy; }
    public void setGivenBy(Long givenBy) { this.givenBy = givenBy; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
