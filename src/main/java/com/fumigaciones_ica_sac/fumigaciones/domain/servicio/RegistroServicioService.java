package com.fumigaciones_ica_sac.fumigaciones.domain.servicio;

import com.fumigaciones_ica_sac.fumigaciones.domain.maquinaria.MaquinariaRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.plaga.PlagaRepository;
import com.fumigaciones_ica_sac.fumigaciones.domain.producto.ProductoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroServicioService {

    private final ServicioRepository servicioRepository;
    private final MaquinariaRepository maquinariaRepository;
    private final PlagaRepository plagaRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public RegistroServicioService(ServicioRepository servicioRepository, MaquinariaRepository maquinariaRepository, PlagaRepository plagaRepository, ProductoRepository productoRepository) {
        this.servicioRepository = servicioRepository;
        this.maquinariaRepository = maquinariaRepository;
        this.plagaRepository = plagaRepository;
        this.productoRepository = productoRepository;
    }

    public RespuestaServicioDTO registrarServicio(RegistrarServicioDTO datos) {
        if (maquinariaRepository.findById(datos.idMaquinaria()).isEmpty()) {
            throw new ValidationException("Este ID para la Maquinaria no fue encontrado");
        }
        if (plagaRepository.findById(datos.idPlaga()).isEmpty()) {
            throw new ValidationException(("Este ID para la Plaga no fue encontrado"));
        }
        if (productoRepository.findById(datos.idProducto()).isEmpty()) {
            throw new ValidationException(("Este ID para el Producto no fue encontrado"));
        }
        var maquinaria = maquinariaRepository.getReferenceById(datos.idMaquinaria());
        var plaga = plagaRepository.getReferenceById(datos.idPlaga());
        var producto = productoRepository.getReferenceById(datos.idProducto());

        var servicio = new Servicio(null, maquinaria, plaga, producto, datos.nombre(), datos.cantidadMaquinaria(), datos.cantidadProducto(), datos.precioHora(), datos.descripcion());
        servicioRepository.save(servicio);

        return new RespuestaServicioDTO(servicio);
    }
}
