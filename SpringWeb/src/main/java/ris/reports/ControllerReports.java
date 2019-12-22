package ris.reports;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Predstava;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/reports")
public class ControllerReports {
	
	@RequestMapping(value="/predstave.pdf", method=RequestMethod.GET) 
	public void generisiIzvestaj(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		List<Predstava> predstave = (List<Predstava>)request.getSession().getAttribute("predstaveR");
	
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(predstave);
		InputStream inputStream = this.getClass().getResourceAsStream("/reports/Predstave.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		String reziser="";
			reziser=predstave.get(0).getReziser().getIme()+" "+predstave.get(0).getReziser().getPrezime();
		if(predstave!=null && predstave.size()>0)
			params.put("reziser", reziser);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=PredstaveRezisera.pdf");
		ServletOutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}

}
