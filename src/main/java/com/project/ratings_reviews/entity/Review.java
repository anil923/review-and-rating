package com.project.ratings_reviews.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "reviews", schema = "rating_review_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reviewId;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reviews_reviewer"))
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewed_user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reviews_reviewed_user"))
    private User reviewedUser;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reviews_ride"))
    private Ride ride;

    @Column(nullable = false)
    private double rating;

    @Column(columnDefinition = "TEXT")
    private String reviewText;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @Column(updatable = false)
    private String createdAt;

    @Column
    private String updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = java.time.LocalDateTime.now().toString();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = java.time.LocalDateTime.now().toString();
    }
}

