package in.uday.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.uday.Model.Employee;
import in.uday.Model.TaxDeductionResponse;
import in.uday.Repo.EmployeeRepo;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepository;

	public TaxDeductionResponse calculateTaxDeduction(Integer employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new EntityNotFoundException("Employee not found"));

		LocalDate dateOfJoining = employee.getDateOfJoining();
		BigDecimal monthlySalary = employee.getSalary();
		long monthsWorked = calculateMonthsWorked(dateOfJoining);

		BigDecimal totalSalary = monthlySalary.multiply(BigDecimal.valueOf(monthsWorked));
		BigDecimal taxAmount = calculateTaxAmount(totalSalary);
		BigDecimal cessAmount = calculateCessAmount(totalSalary);

		return new TaxDeductionResponse(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
				totalSalary, taxAmount, cessAmount);
	}

	private long calculateMonthsWorked(LocalDate dateOfJoining) {
		LocalDate today = LocalDate.now();
		return ChronoUnit.MONTHS.between(dateOfJoining, today);
	}

	private BigDecimal calculateTaxAmount(BigDecimal totalSalary) {
		BigDecimal[] taxSlabs = { BigDecimal.valueOf(250000), BigDecimal.valueOf(500000), BigDecimal.valueOf(1000000) };
		BigDecimal[] taxRates = { BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.2),
				BigDecimal.valueOf(0.3) };
		BigDecimal taxAmount = BigDecimal.ZERO;

		for (int i = 0; i < taxSlabs.length; i++) {
			if (totalSalary.compareTo(taxSlabs[i]) > 0) {
				BigDecimal remainingSalary = totalSalary.subtract(taxSlabs[i]);

				BigDecimal taxSlabAmount = i == 0 ? remainingSalary : remainingSalary.subtract(taxSlabs[i - 1]);
				taxAmount = taxAmount.add(taxSlabAmount.multiply(taxRates[i]));
			}
		}

		return taxAmount;
	}

	private BigDecimal calculateCessAmount(BigDecimal totalSalary) {
		BigDecimal threshold = BigDecimal.valueOf(2500000);
		BigDecimal cessRate = BigDecimal.valueOf(0.02);
		return totalSalary.compareTo(threshold) > 0 ? totalSalary.subtract(threshold).multiply(cessRate)
				: BigDecimal.ZERO;
	}
}
