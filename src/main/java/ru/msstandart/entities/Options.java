package ru.msstandart.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "options")
@NoArgsConstructor
@AllArgsConstructor
public class Options {

    @Id
    private String name;

    private Integer letterNameCost;

    private Integer letterEpitaphCost;

    private Integer digitCost;

}
