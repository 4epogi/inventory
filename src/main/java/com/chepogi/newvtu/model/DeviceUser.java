package com.chepogi.newvtu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "device_users")
@Builder
public class DeviceUser {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name can not be empty")
    @Column(name="first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    @Column(name="last_name", nullable = false)
    private String lastName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;
}
