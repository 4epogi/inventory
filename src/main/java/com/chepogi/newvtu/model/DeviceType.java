package com.chepogi.newvtu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "device_type")
public class DeviceType {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, unique = true)
    @NotEmpty(message = "Device type name can not be empty")
    private String name;
    
//    @OneToMany(mappedBy = "deviceType", targetEntity=DeviceUser.class,
//            cascade = CascadeType.ALL)
//    private List<AppUser> deviceUsers = new ArrayList<>();
}