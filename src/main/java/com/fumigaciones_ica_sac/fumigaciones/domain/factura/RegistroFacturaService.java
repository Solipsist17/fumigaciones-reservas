package com.fumigaciones_ica_sac.fumigaciones.domain.factura;

import com.fumigaciones_ica_sac.fumigaciones.domain.reserva.ReservaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroFacturaService {

    private final FacturaRepository facturaRepository;
    private final ReservaRepository reservaRepository;

    @Autowired
    public RegistroFacturaService(FacturaRepository facturaRepository, ReservaRepository reservaRepository) {
        this.facturaRepository = facturaRepository;
        this.reservaRepository = reservaRepository;
    }

    public RespuestaFacturaDTO registrarFactura(RegistrarFacturaDTO datos) {
        if (reservaRepository.findById(datos.idReserva()).isEmpty()) {
            throw new ValidationException("Este ID para la Reserva no fue encontrado");
        }

        var reserva = reservaRepository.getReferenceById(datos.idReserva());

        var factura = new Factura(null, reserva, datos.cantidadHora(), datos.precioUnitario(), datos.total());
        facturaRepository.save(factura);

        return new RespuestaFacturaDTO(factura);
    }
}
