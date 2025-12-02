package com.example.MoonPhase.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    @Column(name = "IdCategoriaSolicitud", nullable = false)
    private Long idCategoriaSolicitud;

    @Column(name = "IdUsuarioCreacion", nullable = false)
    private Long idUsuarioCreacion;

    @Column(name = "FechaCreacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "IdEstadoSolicitud", nullable = false)
    private Long idEstadoSolicitud;

    @Column(name = "IdPrioridad", nullable = false)
    private Long idPrioridad;
}
