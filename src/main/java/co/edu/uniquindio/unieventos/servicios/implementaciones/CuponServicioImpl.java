package co.edu.uniquindio.unieventos.servicios.implementaciones;
import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.CuponEnviadoDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EliminarCuponDTO;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepository;

    public CuponServicioImpl(CuponRepo cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    @Override
    public String crearCupon(CrearCuponDTO cuponDTO) throws Exception {
        Cupon cupon= new Cupon(cuponDTO.getNombre(),cuponDTO.getDescuento(),cuponDTO.getFechaVencimiento(),cuponDTO.getCodigo(), EstadoCupon.DISPONIBLE,cuponDTO.getTipo());
        Cupon cuponCreado= cuponRepository.save(cupon);
        return "Se ha creado el cupón correctamente";
    }

    @Override
    public String editarCupon(String codigo, EditarCuponDTO cuponDTO) throws Exception {
        Optional<Cupon> cuponOptional= cuponRepository.findById(codigo);
        if (cuponOptional.isEmpty()){
            throw new Exception("No existe el cupon");
        }
        //Campos que se pueden editar
        Cupon cupon= cuponOptional.get();
        cupon.setNombre(cuponDTO.getNombre());
        cupon.setDescuento(cuponDTO.getDescuento());
        cupon.setFechaVencimiento(cuponDTO.getFechaVencimiento());
        //Se guarda la nueva información
        Cupon cuponActualizado= cuponRepository.save(cupon);

        return "Se ha actualizado el cupón correctamente";
    }

    /**
     *
     * @param codigo
     * @param cuponDTO
     * @return
     * @throws Exception
     */
    @Override
    public String eliminarCupon(String codigo, EliminarCuponDTO cuponDTO) throws Exception {
        Cupon cupon= getCuponByCodigo(codigo);
        //Camniamos el estado (No se elimina)
        cupon.setEstado(cuponDTO.getEstado());
        Cupon estadoCupon= cuponRepository.save(cupon);
        return "Se ha cambiado el estado a NO-DISPONIBLE";
    }

    /**
     * Se obtiene el cupón por su código, y al total de la venta se le aplica el descuento y se guarda en una nueva variable(Posible futuro retorno)
     * @param codigo
     * @param total
     * @return
     * @throws Exception
     */
    @Override
    public String redimirCupon(String codigo, float total) throws Exception {
        Cupon cuponARedimir= getCuponByCodigo(codigo);
        if (cuponARedimir==null){
            throw new Exception("No hay ningún cupón con el código ingresado");
        }
        float descuento= cuponARedimir.getDescuento()/100;
        float nuevoTotal= total - (total*descuento);
        return "Se ha redimido el cupón correctamente";
    }

    @Override
    public String enviarCupon(CuponEnviadoDTO cuponEnviado) throws Exception {
        return null;
    }

    //Private methods

    private Cupon getCuponByCodigo(String codigo) throws Exception {
        return cuponRepository.findById(codigo).orElseThrow(()->new Exception("No se ha encontrado"));
    }


}
