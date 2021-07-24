package com.bcp.challenge.exchangerate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "origincurrency")
    private String origincurrency;

    @Column(name = "destinationcurrency")
    private String destinationcurrency;

    @Column(name = "exchangerate")
    private Float exchangerate;
}
