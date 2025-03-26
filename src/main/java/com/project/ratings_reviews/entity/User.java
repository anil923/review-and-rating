package com.project.ratings_reviews.entity;

import com.project.ratings_reviews.Enum.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "rating_review_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(updatable = false)
    private String createdAt;

    // Relationships
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<Review> givenReviews;

    @OneToMany(mappedBy = "reviewedUser", cascade = CascadeType.ALL)
    private List<Review> receivedReviews;

    @OneToMany(mappedBy = "reportedBy", cascade = CascadeType.ALL)
    private List<ReportedReview> reportedReviews;

    @PrePersist
    public void prePersist() {
        this.createdAt = java.time.LocalDateTime.now().toString();
    }
}

