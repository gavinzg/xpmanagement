package com.platform.cloud.xpmanagement.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceLog {
    @org.hibernate.annotations.Type(type="pg-uuid")
    private String experienceLogId;
    private Experience experienceId;
    private int playerId;
    private int amount;
    private String type;
    private String remarks;
    private Timestamp createdAt;

    @Id
    @GeneratedValue(generator = "logId_generator")
    @GenericGenerator(name="logId_generator", strategy = "uuid")
    @Column(name = "experience_log_id", nullable = false, length = 24)
    public String getExperienceLogId() {
        return experienceLogId;
    }

    public void setExperienceLogId(String experienceLogId) {
        this.experienceLogId = experienceLogId;
    }

    @ManyToOne
    @JoinColumn(name = "experience_id", nullable = false)
    public Experience getExperience() {
        return experienceId;
    }

    public void setExperience(Experience experienceId) {
        this.experienceId = experienceId;
    }

    @Basic
    @Column(name = "player_id", nullable = false)
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceLog that = (ExperienceLog) o;
        return experienceLogId.equals(that.experienceLogId) &&
                experienceId.getExperienceId().equals(that.experienceId.getExperienceId()) &&
                playerId == that.playerId &&
                amount == that.amount &&
                type.equals(that.type) &&
                remarks.equals(that.remarks) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experienceLogId, experienceId.getExperienceId(), playerId, amount, type, remarks, createdAt);
    }
}

