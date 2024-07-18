package com.fumigaciones_ica_sac.fumigaciones.domain.factura;

import com.fumigaciones_ica_sac.fumigaciones.utils.FacturaReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class ReporteFacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private FacturaReportGenerator facturaReportGenerator;

    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return facturaReportGenerator.exportToPdf((List<Factura>) facturaRepository.findAll().stream().map(factura -> new ListadoFacturaDTO(factura)));
    }

    public byte[] exportXls() throws JRException, FileNotFoundException {
        //return facturaReportGenerator.exportToXls(facturaRepository.findAll());
        return facturaReportGenerator.exportToXls((List<Factura>) facturaRepository.findAll().stream().map(factura -> new ListadoFacturaDTO(factura)));
    }

}
