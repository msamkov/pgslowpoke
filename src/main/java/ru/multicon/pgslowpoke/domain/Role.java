package ru.multicon.pgslowpoke.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
