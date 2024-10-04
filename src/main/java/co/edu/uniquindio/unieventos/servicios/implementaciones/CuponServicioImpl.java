package co.edu.uniquindio.unieventos.servicios.implementaciones;
import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.CuponEnviadoDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EliminarCuponDTO;
import co.edu.uniquindio.unieventos.exceptions.CuponException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.IllegalFormatCodePointException;
import java.util.List;

@Service
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepository;

    public CuponServicioImpl(CuponRepo cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    @Override
    public Cupon crearCupon(CrearCuponDTO cuponDTO) throws CuponException {
        Cupon cupon= new Cupon(cuponDTO.getNombre(),cuponDTO.getDescuento(),cuponDTO.getFechaVencimiento(),cuponDTO.getCodigo(), EstadoCupon.DISPONIBLE,cuponDTO.getTipo());
        return  cuponRepository.save(cupon);
    }

    @Override
    public Cupon editarCupon(String codigo, EditarCuponDTO cuponDTO) throws CuponException {
        Cupon cupon= getCuponByCodigo(codigo);
        if (cupon==null){
            throw new CuponException("No existe el cupon");
        }
        //Campos que se pueden editar
        cupon.setNombre(cuponDTO.getNombre());
        cupon.setDescuento(cuponDTO.getDescuento());
        cupon.setFechaVencimiento(cuponDTO.getFechaVencimiento());
        //Se guarda la nueva información
        return  cuponRepository.save(cupon);
    }

    /**
     *
     * @param codigo
     * @return
     * @throws Exception
     */
    @Override
    public Cupon eliminarCupon(String codigo) throws CuponException {
        Cupon cupon= getCuponByCodigo(codigo);
        if(cupon==null){
            throw new CuponException("No existe el cupón");
        }
        //Camniamos el estado (No se elimina)
        cupon.setEstado(EstadoCupon.NO_DISPONIBLE);
        return cuponRepository.save(cupon);

    }

    /**
     * Se obtiene el cupón por su código, y al total de la venta se le aplica el descuento y se guarda en una nueva variable(Posible futuro retorno)
     * @param codigo
     * @param total
     * @return
     * @throws Exception
     */
    @Override
    public float redimirCupon(String codigo, float total) throws Exception {
        Cupon cuponARedimir= getCuponByCodigo(codigo);
        if (cuponARedimir==null){
            throw new Exception("No hay ningún cupón con el código ingresado");
        }
        float descuento= cuponARedimir.getDescuento()/100;
        return total - (total*descuento);//Retorna el nuevo valor
    }

    @Override
    public String enviarCupon(CuponEnviadoDTO cuponEnviado) throws Exception {
        return null;
    }

    @Override
    public Cupon getCuponByCodigo(String codigo) throws CuponException {
        return cuponRepository.findById(codigo).orElseThrow(()->new CuponException("No se ha encontrado ningún cupon"));
    }
    @Override
    public boolean verificarVigencia(Cupon cuponAux){
        return cuponAux.getFechaVencimiento().isBefore(LocalDateTime.now());
    }

    public List<Cupon> getAll(){
        return cuponRepository.findAll();
    }
}
