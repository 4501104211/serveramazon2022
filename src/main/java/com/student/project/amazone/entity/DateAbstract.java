package com.student.project.amazone.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class DateAbstract implements Serializable {
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Calendar LastUpdated;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Calendar CreateAt;

    @PrePersist
    public void prePersist() {
        if (CreateAt == null) {
            CreateAt  = Calendar.getInstance();
        }
    }

    @PreUpdate
    public void preUpdate() {
        Date date = new Date();;
        LastUpdated = Calendar.getInstance();
    }
}
