package com.voluntrack.dto.landing;

public class TeamMemberDTO {
    private String name;
    private String role;
    private String bio;
    private String imageUrl;

    public TeamMemberDTO() {}

    public TeamMemberDTO(String name, String role, String bio, String imageUrl) {
        this.name = name;
        this.role = role;
        this.bio = bio;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
