package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class DateAbstract implements Serializable {
    private static final String MY_TIME_ZONE="Asia/Ho_Chi_Minh";
    private static final String PATTERN = "dd/MM/yyyy'T'HH:mm:ss";
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern=PATTERN,timezone = MY_TIME_ZONE)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    @JsonProperty("LastUpdated")
    private Date LastUpdated;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern=PATTERN,timezone = MY_TIME_ZONE)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    @JsonProperty("CreateAt")
    private Date CreateAt;

    @PrePersist
    public void prePersist() {
        if (CreateAt == null) {
            this.CreateAt  =  new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        Date date = new Date();;
        this.LastUpdated  =  new Date();
    }
}
