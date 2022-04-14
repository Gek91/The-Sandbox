import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main {

	public static void main(String[] args) throws IOException {
		byte[] result;
		try {
				
			InputStream templateContentInputStream = new FileInputStream(new java.io.File(Main.class.getClassLoader().getResource("Companies.jasper").toURI()));
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(templateContentInputStream);
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("root_path", "/Users/gek/Dev/Git/Java/");
//			parameters.put("ReportTitle", "mio report");
			
			JRDataSource dataSource = new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(buildData());
			
//			JasperReport jasperReport = JasperCompileManager.compileReport(templateContentInputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			result = JasperExportManager.exportReportToPdf(jasperPrint);
			
			try (FileOutputStream stream = new FileOutputStream("result.pdf")) {
			    stream.write(result);
			}
		} catch (JRException | URISyntaxException e) {
			throw new IllegalStateException("Unable to create report", e);
		}
			
	}
	
	private static List<Company> buildData() {
		
		List<Company> companies = new ArrayList<>();
		
		Company company1 = new Company();
		company1.setName("company 1");
		List<Employee> employees = new ArrayList<>();
		Employee employee1 = new Employee();
		employee1.setFirstname("name1");
		employee1.setLastname("lastname1");
		employees.add(employee1);
		Employee employee2 = new Employee();
		employee2.setFirstname("name2");
		employee2.setLastname("lastname2");
		employees.add(employee2);
		company1.setEmployees(employees);
		companies.add(company1);
		
		Company company2 = new Company();
		company2.setName("company 2");
		employees = new ArrayList<>();
		Employee employee3 = new Employee();
		employee3.setFirstname("name3");
		employee3.setLastname("lastname3");
		employees.add(employee3);
		Employee employee4 = new Employee();
		employee4.setFirstname("name4");
		employee4.setLastname("lastname4");
		employees.add(employee4);
		company2.setEmployees(employees);
		companies.add(company2);
		
		return companies;
	}
}
