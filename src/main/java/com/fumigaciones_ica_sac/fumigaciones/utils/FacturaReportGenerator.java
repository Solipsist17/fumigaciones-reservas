package com.fumigaciones_ica_sac.fumigaciones.utils;

import com.fumigaciones_ica_sac.fumigaciones.domain.factura.Factura;
import com.fumigaciones_ica_sac.fumigaciones.domain.factura.ListadoFacturaDTO;
import com.fumigaciones_ica_sac.fumigaciones.domain.factura.ReporteFacturaDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacturaReportGenerator {
    public byte[] exportToPdf(List<ReporteFacturaDTO> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    public byte[] exportToXls(List<ReporteFacturaDTO> list) throws JRException, FileNotFoundException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getReport(List<ReporteFacturaDTO> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        //params.put("facturasData", new JRBeanCollectionDataSource(list));
        params.put("ds", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:reports/facturasReport.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }
}
