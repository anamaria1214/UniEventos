package co.edu.uniquindio.unieventos.servicios.implementaciones;
import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.CuponEnviadoDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EliminarCuponDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import org.springframework.stereotype.Service;

@Service
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepository;

    public CuponServicioImpl(CuponRepo cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    @Override
    public String crearCupon(CrearCuponDTO cuponDTO) throws Exception {
        Cupon cupon= new Cupon(cuponDTO.getNombre(),cuponDTO.getDescuento(),cuponDTO.getFechaVencimiento(),cuponDTO.getCodigo(), cuponDTO.getEstado(),cuponDTO.getTipo());
        Cupon cuponCreado= cuponRepository.save(cupon);
        return "Se ha creado el cupón correctamente";
    }

    @Override
    public String editarCupon(String id, EditarCuponDTO cuponDTO) throws Exception {
        Cupon cupon= getCuponById(id);
        //Campos que se pueden editar
        cupon.setNombre(cuponDTO.getNombre());
        cupon.setDescuento(cuponDTO.getDescuento());
        cupon.setFechaVencimiento(cuponDTO.getFechaVencimiento());
        //Se guarda la nueva información
        Cupon cuponActualizado= cuponRepository.save(cupon);

        return "Se ha actualizado el cupón correctamente";
    }

    /**
     *
     * @param id
     * @param cuponDTO
     * @return
     * @throws Exception
     */
    @Override
    public String eliminarCupon(String id, EliminarCuponDTO cuponDTO) throws Exception {
        Cupon cupon= getCuponById(id);
        //Camniamos el estado (No se elimina)
        cupon.setEstado(cuponDTO.getEstado());
        Cupon estadoCupon= cuponRepository.save(cupon);
        return "Se ha cambiado el estado a NO-DISPONIBLE";
    }

    @Override
    public String redimirCupon(String codigo) throws Exception {
        return null;
    }

    @Override
    public String enviarCupon(CuponEnviadoDTO cuponEnviado) throws Exception {
        return null;
    }

    //Private methods

    private Cupon getCuponById(String id) throws Exception {
        return cuponRepository.findById(id).orElseThrow(()->new Exception("No se ha encontrado"));
    }


}
