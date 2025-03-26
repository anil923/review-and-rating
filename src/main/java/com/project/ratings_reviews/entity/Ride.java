package com.project.ratings_reviews.entity;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "rides", schema = "rating_review_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rideId;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rides_driver"))
    private User driver;

    @ManyToOne
    @JoinColumn(name = "rider_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rides_rider"))
    private User rider;
    private String status;
    @Column(updatable = false)
    private String createdAt;

    // Relationships
    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @PrePersist
    public void prePersist() {
        this.createdAt = java.time.LocalDateTime.now().toString();
    }
}
