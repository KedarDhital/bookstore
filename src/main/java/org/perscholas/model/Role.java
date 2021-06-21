package org.perscholas.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(nullable = false,unique = true)
    @NotEmpty
    String name;

    // one role can have many users
    @ManyToMany(mappedBy = "roles")
    List<User> users;
}
