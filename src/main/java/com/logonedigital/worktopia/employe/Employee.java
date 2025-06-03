package com.logonedigital.worktopia.employe;

import com.logonedigital.worktopia.common.BaseEntity;
import com.logonedigital.worktopia.paie.PaySlip;
import com.logonedigital.worktopia.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
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
    private String email;
    private String position;
    private String phone;
    private String address;
    private Department department;
    private String gender;
    private  String  dateofbirth;
    private String manager;
    private Date dateembauche;
    private String typecontrat;
    private long salairebrut;
    private  String iban;
    private Availability availability;



    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private User user;
    @OneToMany(mappedBy = "employee")
    private List<PaySlip> paySlips;



    public String getFullName() {
        return firstName + " " + lastName;
    }

}