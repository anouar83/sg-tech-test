package com.sg.domain;
import com.sg.util.InjectUUID;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee extends LoggedModel {

    @Id
    @Getter
    @Setter
    @Column(length = 36)
    private UUID employeeId;

    @Getter
    @Setter
    @Column(name = "NOM", nullable = false, unique = true)
    private String nom;

    @Getter
    @Setter
    @Column(name = "PRENOM", nullable = false)
    private String prenom;

    @ManyToMany(cascade = {CascadeType.ALL})
            @JoinTable(
                    name = "Employee_Projet",
                    joinColumns = {@JoinColumn(name = "employee_id")},
                    inverseJoinColumns = {@JoinColumn(name = "projet_id")}
            )
    Set<Projet> projets = new HashSet<>();
}
