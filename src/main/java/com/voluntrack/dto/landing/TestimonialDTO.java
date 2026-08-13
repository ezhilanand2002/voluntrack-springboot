package com.voluntrack.dto.landing;

public class TestimonialDTO {
    private String quote;
    private String authorName;
    private String authorTitle;
    private String avatarUrl;
    private String linkedinUrl;

    public TestimonialDTO() {}

    public TestimonialDTO(String quote, String authorName, String authorTitle, String avatarUrl, String linkedinUrl) {
        this.quote = quote;
        this.authorName = authorName;
        this.authorTitle = authorTitle;
        this.avatarUrl = avatarUrl;
        this.linkedinUrl = linkedinUrl;
    }

    public String getQuote() { return quote; }
    public void setQuote(String quote) { this.quote = quote; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getAuthorTitle() { return authorTitle; }
    public void setAuthorTitle(String authorTitle) { this.authorTitle = authorTitle; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }
}
