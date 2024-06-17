package com.fumigaciones_ica_sac.fumigaciones.domain.reserva;

import com.fumigaciones_ica_sac.fumigaciones.domain.cliente.ClienteRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.maquinaria.MaquinariaRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.plaga.PlagaRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.producto.ProductoRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.servicio.RegistrarServicioDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.servicio.RespuestaServicioDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.servicio.Servicio;
import com.fumigaciones_ica_sac.fumigaciones.domain.servicio.ServicioRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ServicioRepository servicioRepository;

    @Autowired
    public RegistroReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository,
                                  UsuarioRepository usuarioRepository, ServicioRepository servicioRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicioRepository = servicioRepository;
    }


    public RespuestaReservaDTO registrarReserva(RegistrarReservaDTO datos) {
        if (clienteRepository.findById(datos.idCliente()).isEmpty()) {
            throw new ValidationException("Este ID para el Cliente no fue encontrado");
        }
        if (usuarioRepository.findById(datos.idUsuario()).isEmpty()) {
            throw new ValidationException(("Este ID para el Usuario no fue encontrado"));
        }
        if (servicioRepository.findById(datos.idServicio()).isEmpty()) {
            throw new ValidationException(("Este ID para el Servicio no fue encontrado"));
        }
        var cliente = clienteRepository.getReferenceById(datos.idCliente());
        var usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        var servicio = servicioRepository.getReferenceById(datos.idServicio());

        var reserva = new Reserva(null, cliente, usuario, servicio, datos.cantidadPersonal(), datos.fechaReserva(), datos.descripcion());
        reservaRepository.save(reserva);

        return new RespuestaReservaDTO(reserva);
    }
}
