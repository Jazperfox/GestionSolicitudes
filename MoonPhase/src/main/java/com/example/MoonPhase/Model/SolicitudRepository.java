package com.example.MoonPhase.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query("SELECT s FROM Solicitud s WHERE s.idUsuario IS NULL")
    List<Solicitud> findSolicitudesNoAsignadasJPQL();
}
