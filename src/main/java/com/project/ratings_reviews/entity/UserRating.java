package com.project.ratings_reviews.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "user_ratings", schema = "rating_review_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {

    @Id
    @JoinColumn(name = "reviewed_user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_ratings_reviewed_user"))
    private UUID reviewedUserId;

    @Column(nullable = false)
    private double averageRating = 0.0;

    @Column(nullable = false)
    private int totalReviews = 0;
}

