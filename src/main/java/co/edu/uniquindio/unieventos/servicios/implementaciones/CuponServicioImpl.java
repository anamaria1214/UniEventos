package co.edu.uniquindio.unieventos.servicios.implementaciones;
import co.edu.uniquindio.unieventos.dto.CrearCuponDTO;
import co.edu.uniquindio.unieventos.dto.EditarCuponDTO;
import co.edu.uniquindio.unieventos.dto.EmailDTO;
import co.edu.uniquindio.unieventos.exceptions.CuentaException;
import co.edu.uniquindio.unieventos.exceptions.CuponException;
import co.edu.uniquindio.unieventos.modelo.documentos.Cuenta;
import co.edu.uniquindio.unieventos.modelo.documentos.Cupon;
import co.edu.uniquindio.unieventos.modelo.enums.EstadoCupon;
import co.edu.uniquindio.unieventos.modelo.enums.TipoCupon;
import co.edu.uniquindio.unieventos.repositorios.CuentaRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.CuponServicio;
import co.edu.uniquindio.unieventos.repositorios.CuponRepo;
import co.edu.uniquindio.unieventos.servicios.interfaces.EmailServicio;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepository;
    private final CuentaRepo cuentaRepository;
    private final EmailServicio emailServicio;
    public CuponServicioImpl(CuponRepo cuponRepository, CuentaRepo cuentaRepository, EmailServicio emailServicio) {
        this.cuponRepository = cuponRepository;
        this.cuentaRepository = cuentaRepository;
        this.emailServicio = emailServicio;
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
        cupon.setTipo(cuponDTO.getTipo());
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
        //Cambiamos el estado (No se elimina)
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
    public void enviarCupon(String email) throws Exception {
        Optional<Cuenta> cuenta= cuentaRepository.buscarEmail(email);
        if (cuenta.isEmpty()){
            throw new CuentaException("No existe la cuenta");
        }
        Cuenta cuentaUser= cuenta.get();
        String codigoCupon= generarCodigoValidacion();
        String nombre = "Descuento de Bienvenida";
        float descuento = 15.5f;
        LocalDateTime fechaVencimiento = LocalDateTime.of(2024, 12, 31, 23, 59);
        TipoCupon tipo = TipoCupon.UNICO;
        Cupon nuevoCupon = new Cupon(nombre, descuento, fechaVencimiento, codigoCupon,EstadoCupon.DISPONIBLE ,tipo);
        emailServicio.enviarCorreo(new EmailDTO("Bienvenido, gracias por registrarte", "Al registrarte tienes un código del 15% de descunto: "+codigoCupon+" este código es redimible una vez en cualquier orden", email));
        cuponRepository.save(nuevoCupon);
    }

    @Override
    public Cupon getCuponByCodigo(String codigo) throws CuponException {
        return cuponRepository.findById(codigo).orElseThrow(()->new CuponException("No se ha encontrado ningún cupon"));
    }
    @Override
    public boolean verificarVigencia(Cupon cuponAux){
        return cuponAux.getFechaVencimiento().isBefore(LocalDateTime.now());
    }
    @Override
    public List<Cupon> getAll(){
        return cuponRepository.findAll();
    }

    //--------------PRIVATES---------------
    private String generarCodigoValidacion(){

        String cadena ="ABCDEFGHIJKMNÑOPQRSTUVWXYZ1234567890";
        String resul="";

        for(int i=0; i<6;i++){
            int indice = (int) (Math.random()*cadena.length());
            char car= cadena.charAt(indice);
            resul+=car;
        }
        return resul;
    }


}
