package com.logonedigital.worktopia.employe;

import com.logonedigital.worktopia.common.BaseEntity;
import com.logonedigital.worktopia.paie.PaySlip;
import com.logonedigital.worktopia.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private Department department;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    //Infos sur les profils
    private String photoLink;
    private String curriculumVitaeLink;
    @Enumerated(EnumType.STRING)
    private Availability availability;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "employee")
    private List<PaySlip> paySlips;



    public String getFullName() {
        return firstName + " " + lastName;
    }

}