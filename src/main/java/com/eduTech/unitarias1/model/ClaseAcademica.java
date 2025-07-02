package com.eduTech.unitarias1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Clase_Academica")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClaseAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
}