package com.chepogi.newvtu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "departments")
@Builder
public class Department {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, unique = true)
    @NotEmpty(message = "Department name cannot be empty")
    private String name;
    
    @OneToMany(mappedBy = "department", targetEntity=DeviceUser.class,
    cascade = CascadeType.ALL)
    private List<DeviceUser> deviceUsers = new ArrayList<>();
}
