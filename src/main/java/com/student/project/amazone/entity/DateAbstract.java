package com.student.project.amazone.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class DateAbstract implements Serializable {
    @JsonFormat(pattern="yyyy-MM-dd ")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private ZonedDateTime LastUpdated;

    @JsonFormat(pattern="yyyy-MM-dd ")
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private ZonedDateTime CreateAt;


    @PrePersist
    public void prePersist() {
        if (CreateAt == null) {
            Instant nowUtc = Instant.now();
            ZoneId asiaSingapore = ZoneId.of("Asia/Ho_Chi_Minh");
            ZonedDateTime nowAsiaSingapore = ZonedDateTime.ofInstant(nowUtc, asiaSingapore);
            this.CreateAt  =  nowAsiaSingapore;
        }
    }

    @PreUpdate
    public void preUpdate() {
        Instant nowUtc = Instant.now();
        ZoneId asiaSingapore = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime nowAsiaSingapore = ZonedDateTime.ofInstant(nowUtc, asiaSingapore);
        this.LastUpdated  =  nowAsiaSingapore;
    }
}
