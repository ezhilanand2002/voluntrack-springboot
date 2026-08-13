package com.voluntrack.dto;

public class FeedbackDTO {
    private Integer rating;
    private String comment;

    public FeedbackDTO() {}

    public FeedbackDTO(Integer rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
