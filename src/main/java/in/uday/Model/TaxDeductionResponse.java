package in.uday.Model;
import java.math.BigDecimal;

import jakarta.persistence.Table;


@Table(name="TaxDeductionResponse")
public class TaxDeductionResponse {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private BigDecimal yearlySalary;
    private BigDecimal taxAmount;
    private BigDecimal cessAmount;
    
    
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigDecimal getYearlySalary() {
		return yearlySalary;
	}
	public void setYearlySalary(BigDecimal yearlySalary) {
		this.yearlySalary = yearlySalary;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getCessAmount() {
		return cessAmount;
	}
	public void setCessAmount(BigDecimal cessAmount) {
		this.cessAmount = cessAmount;
	}
	@Override
	public String toString() {
		return "TaxDeductionResponse [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", yearlySalary=" + yearlySalary + ", taxAmount=" + taxAmount + ", cessAmount=" + cessAmount + "]";
	}
	public TaxDeductionResponse(Integer employeeId, String firstName, String lastName, BigDecimal yearlySalary,
			BigDecimal taxAmount, BigDecimal cessAmount) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearlySalary = yearlySalary;
		this.taxAmount = taxAmount;
		this.cessAmount = cessAmount;
	}
    
    
    
	
    
    
    
    

    
    
   
}
