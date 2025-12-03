package com.example.MoonPhase.Controller;

import com.example.MoonPhase.Model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// import java.time.LocalDate;

@Controller
@RequestMapping("/solicitud")
public class SolicitudController {
    private final SolicitudRepository solicitudRepo;
    private final CategoriaSolicitudRepository categoriaRepo;
    private final AppUsuarioRepository usuarioRepo;

    public SolicitudController(SolicitudRepository solicitudRepo,
                               CategoriaSolicitudRepository categoriaRepo,
                               AppUsuarioRepository usuarioRepo) {
        this.solicitudRepo = solicitudRepo;
        this.categoriaRepo = categoriaRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/crear")
    public String mostrarFormulario(Model model) {
        model.addAttribute("categorias", categoriaRepo.findAll());
        model.addAttribute("solicitud", new Solicitud());
        return "crearSolicitud";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Solicitud solicitud) {

        solicitud.setIdPrioridad(1L);
        solicitud.setIdEstadoSolicitud(1L);
        solicitud.setFechaCreacion(new java.sql.Date(System.currentTimeMillis()));

        solicitudRepo.save(solicitud);
        return "redirect:/index";
    }

    @GetMapping("/pendientes")
    public String solicitudesPendientes(Model model) {
        //Busca solicitudes pendientes con idusuario = null
        List<Solicitud> pendientes = solicitudRepo.findSolicitudesNoAsignadasJPQL();
        // Carga los usuarios
        List<AppUsuario> usuarios = usuarioRepo.findAll();

        model.addAttribute("pendientes", pendientes);
        model.addAttribute("usuarios", usuarios);

        return "pendientes";
    }

    @PostMapping("/asignar")
    public String asignarSolicitud(@RequestParam("idSolicitud") Long idSolicitud,
                                   @RequestParam("idUsuario") Long idUsuario) {

        //busca solicitud por id
        Solicitud solicitud = solicitudRepo.findById(idSolicitud)
                .orElseThrow(() -> new IllegalArgumentException("ID de solicitud inválida: " + idSolicitud));
        //asigna el nuevo id de usuario a la solicitud
        solicitud.setIdUsuario(idUsuario);
        //cambia el estado a EN PROCESO
        solicitud.setIdEstadoSolicitud(3L);
        // Guardar los cambios
        solicitudRepo.save(solicitud);

        return "redirect:/solicitud/pendientes";
    }
}
