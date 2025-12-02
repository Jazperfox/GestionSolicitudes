package com.example.MoonPhase.Controller;

import com.example.MoonPhase.Model.CategoriaSolicitudRepository;
import com.example.MoonPhase.Model.Solicitud;
import com.example.MoonPhase.Model.SolicitudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import java.time.LocalDate;

@Controller
@RequestMapping("/solicitud")
public class SolicitudController {

    private final SolicitudRepository solicitudRepo;
    private final CategoriaSolicitudRepository categoriaRepo;

    public SolicitudController(SolicitudRepository solicitudRepo,
                               CategoriaSolicitudRepository categoriaRepo) {
        this.solicitudRepo = solicitudRepo;
        this.categoriaRepo = categoriaRepo;
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

    
}
