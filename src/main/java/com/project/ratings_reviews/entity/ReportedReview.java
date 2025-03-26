package com.project.ratings_reviews.entity;


import com.project.ratings_reviews.Enum.ReportedReviewStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "reported_reviews", schema = "rating_review_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportedReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reportId;

    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reported_reviews_review"))
    private Review review;

    @ManyToOne
    @JoinColumn(name = "reported_by", nullable = false, foreignKey = @ForeignKey(name = "fk_reported_reviews_reported_by"))
    private User reportedBy;

    @Column(nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportedReviewStatus reviewStatus = ReportedReviewStatus.REPORTED;

    @Column(updatable = false)
    private String createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = java.time.LocalDateTime.now().toString();
    }
}


