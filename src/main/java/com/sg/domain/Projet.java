package com.sg.domain;

import com.sg.util.InjectUUID;
import lombok.Getter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projet")
public class Projet extends LoggedModel {

    @Id
    @InjectUUID
    @Getter
    @Column(length = 36)
    private String projetId;

    @ManyToMany(mappedBy = "projets")
    private Set<Employee> employees = new HashSet<>();
}
