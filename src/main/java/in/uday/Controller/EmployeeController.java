package in.uday.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.uday.Model.TaxDeductionResponse;
import in.uday.Service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/tax-deduction/{employeeId}")
	public ResponseEntity<Object> calculateTaxDeduction(@PathVariable Long employeeId) {
		try {
			TaxDeductionResponse taxDeduction = employeeService.calculateTaxDeduction(employeeId);
			return ResponseEntity.ok(taxDeduction);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
		}
	}

}
