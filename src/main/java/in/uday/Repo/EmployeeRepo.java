package in.uday.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.uday.Model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	Employee findById(long id);

}
