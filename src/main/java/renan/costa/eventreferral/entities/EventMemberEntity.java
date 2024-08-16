package renan.costa.eventreferral.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "members")
public class EventMemberEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String name;

    private Integer points;

    private String referralId;

    @CreatedDate
    private Instant createdAt;

    public EventMemberEntity(String id, String email, String name, Integer points, String referralId, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.points = points;
        this.referralId = referralId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
