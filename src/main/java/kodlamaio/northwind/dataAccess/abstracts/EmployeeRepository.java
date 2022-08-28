package kodlamaio.northwind.dataAccess.abstracts;

import kodlamaio.northwind.entities.concretes.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
