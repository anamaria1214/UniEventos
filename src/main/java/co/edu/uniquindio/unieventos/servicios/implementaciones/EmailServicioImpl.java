package co.edu.uniquindio.unieventos.servicios.implementaciones;

import co.edu.uniquindio.unieventos.dto.EmailDTO;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import org.springframework.scheduling.annotation.Async;

public class EmailServicioImpl implements EmailServicio {
    @Override
    @Async
    public void enviarCorreo(EmailDTO emailDTO) {

    }
}
