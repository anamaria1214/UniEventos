package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.CrearOrdenDTO;
import co.edu.uniquindio.unieventos.dto.InformacionOrdenCompraDTO;
import co.edu.uniquindio.unieventos.dto.ItemOrdenDTO;
import co.edu.uniquindio.unieventos.dto.MensajeDTO;
import co.edu.uniquindio.unieventos.dto.global.MessageDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Orden;
import co.edu.uniquindio.unieventos.servicios.interfaces.OrdenServicio;
import com.mercadopago.resources.preference.Preference;
import jakarta.validation.Valid;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orden")

public class OrdenController {
    @Autowired
    OrdenServicio ordenServicio;

    @GetMapping
    public ResponseEntity<List<Orden>> getAll(){
        return ResponseEntity.ok(ordenServicio.getAll());
    }

    @GetMapping("/infoOrden/{id}")
    public ResponseEntity<MensajeDTO<InformacionOrdenCompraDTO>> getOne(@PathVariable ("id")String id) throws Exception {
        return ResponseEntity.ok(new MensajeDTO<>(false, ordenServicio.obtenerInformacionOrden(id)));
    }

    @PostMapping("/crearOrden/save")
    public ResponseEntity<MensajeDTO<String>> save(@Valid @RequestBody CrearOrdenDTO crearOrdenDTO) throws Exception {
        Orden nuevaOrden = ordenServicio.crearOrden(crearOrdenDTO);
        String message = "Orden" + nuevaOrden.getId() + " creada con exito";
        return ResponseEntity.ok(new MensajeDTO<>(false, message));
    }
    @DeleteMapping("/cancelarOrden/{id}")
    public ResponseEntity<MensajeDTO<String>> delete(@PathVariable("id")String id) throws Exception {
        ordenServicio.cancelarOrden(id);
        return ResponseEntity.ok(new MensajeDTO<>(false, "La orden ha sido cancela exitosamente!"));
    }
    @GetMapping("/historial/{id}")
    public ResponseEntity<MensajeDTO<List<ItemOrdenDTO>>> getHistorial(@PathVariable ("id")String id) throws Exception {
        return ResponseEntity.ok(new MensajeDTO<> (false, ordenServicio.obtenerHistorialOrdenes(id)));
    }
    @PostMapping("/realizar-pago/{idOrden}")
    public ResponseEntity<MensajeDTO<Preference>> realizarPago(@PathVariable("idOrden") String idOrden) throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ordenServicio.realizarPago(idOrden)));
    }


}
