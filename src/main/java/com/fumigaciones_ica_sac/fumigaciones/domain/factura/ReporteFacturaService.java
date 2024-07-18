package com.fumigaciones_ica_sac.fumigaciones.domain.factura;

import com.fumigaciones_ica_sac.fumigaciones.utils.FacturaReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteFacturaService {

    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private FacturaReportGenerator facturaReportGenerator;

    /*
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return facturaReportGenerator.exportToPdf(facturaRepository.findAll());
    }
    */
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        List<ReporteFacturaDTO> facturasDTO = facturaRepository.findAll().stream()
                .map(factura -> new ReporteFacturaDTO(factura))
                .collect(Collectors.toList());
        return facturaReportGenerator.exportToPdf(facturasDTO);
    }

    public byte[] exportXls() throws JRException, FileNotFoundException {
        //return facturaReportGenerator.exportToXls(facturaRepository.findAll());
        List<ReporteFacturaDTO> facturasDTO = facturaRepository.findAll().stream()
                .map(factura -> new ReporteFacturaDTO(factura))
                .collect(Collectors.toList());
        return facturaReportGenerator.exportToXls(facturasDTO);
    }

}
