package co.edu.uniquindio.unieventos.controller;


import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.ImagenesServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/imagenes")
public class ImagenesController {

    private final ImagenesServicio imagenesServicio;

    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO<String>>subir(@RequestParam ("imagen") MultipartFile imagen) throws Exception{
        String mensaje= imagenesServicio.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,mensaje));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDTO<String>>eliminar(@RequestParam ("idImagen") String idImagen) throws Exception{
        imagenesServicio.eliminarImagen(idImagen);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "La imagen fue eliminada correctamente"));
    }



}
