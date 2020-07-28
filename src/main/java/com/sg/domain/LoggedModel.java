package com.sg.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class LoggedModel implements Serializable {

    @CreationTimestamp
    @Column(name="creation_date")
    private LocalDateTime creationDate;

    @CreationTimestamp
    @Column(name="modification_date")
    private LocalDateTime modificationDate;

    @PrePersist
    public void initTimeStamps(){
        if(creationDate == null){
            creationDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void updateTimeStamps(){
        modificationDate = LocalDateTime.now();
    }
}
