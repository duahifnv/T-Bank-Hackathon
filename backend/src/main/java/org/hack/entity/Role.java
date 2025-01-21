package org.hack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles", schema = "public", catalog = "tbank_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @Basic
    @Column(name = "name")
    private String name;
}
