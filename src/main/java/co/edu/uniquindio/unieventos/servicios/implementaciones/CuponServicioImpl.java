package co.edu.uniquindio.unieventos.servicios.implementaciones;
import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.CuponEnviadoDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;

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
    public String redimirCupon(String codigo) throws Exception {
        return null;
    }

    @Override
    public String enviarCupon(CuponEnviadoDTO cuponEnviado) throws Exception {
        return null;
    }
}
