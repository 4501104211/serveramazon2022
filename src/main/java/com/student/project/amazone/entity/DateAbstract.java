package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class DateAbstract implements Serializable {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Paris")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date LastUpdated;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Paris")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date CreateAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Paris")
    @PrePersist
    public void prePersist() {
        if (CreateAt == null) {
            this.CreateAt  =  new Date();
        }
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Paris")
    @PreUpdate
    public void preUpdate() {

        this.LastUpdated  =  new Date();
    }
}
