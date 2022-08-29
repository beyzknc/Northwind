package kodlamaio.northwind.business.concretes;

import kodlamaio.northwind.business.abstracts.EmployeeService;
import kodlamaio.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.northwind.business.requests.employeeRequests.DeleteEmployeeRequest;
import kodlamaio.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import kodlamaio.northwind.business.responses.employees.ListEmployeeResponse;
import kodlamaio.northwind.business.responses.employees.ReadEmployeeResponse;
import kodlamaio.northwind.core.utilities.mapping.ModelMapperService;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;
import kodlamaio.northwind.core.utilities.Results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.Results.SuccessResult;
import kodlamaio.northwind.core.utilities.exceptions.BusinessException;
import kodlamaio.northwind.dataAccess.abstracts.EmployeeRepository;
import kodlamaio.northwind.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeManager implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public Result add(CreateEmployeeRequest createEmployeeRequest) {
        checkIEmployeeReportLimitExceeds(createEmployeeRequest.getReportsTo());
        Employee employee = this.modelMapperService.forRequest().map(createEmployeeRequest, Employee.class);
        employeeRepository.save(employee);
        return new SuccessResult("Added successfully");
    }

    @Override
    public Result update(UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = this.modelMapperService.forRequest().map(updateEmployeeRequest, Employee.class);
        employeeRepository.save(employee);
        return new SuccessResult("Updated successfully");
    }

    @Override
    public Result delete(DeleteEmployeeRequest deleteEmployeeRequest) {
        Employee employee = this.modelMapperService.forRequest().map(deleteEmployeeRequest, Employee.class);
        this.employeeRepository.deleteById(employee.getEmployeeId());
        return new SuccessResult("Deleted successfully");
    }

    @Override
    public DataResult<List<ListEmployeeResponse>> getAll() {
        List<Employee> result = this.employeeRepository.findAll();
        List<ListEmployeeResponse> response =
                result.stream().map(item -> this.modelMapperService.forResponse().map(item, ListEmployeeResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListEmployeeResponse>>(response,"Listed Successfully");
    }

    @Override
    public  DataResult<ReadEmployeeResponse> getById( int employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).get();
        ReadEmployeeResponse response=modelMapperService.forResponse().map(employee, ReadEmployeeResponse.class);
        return new SuccessDataResult<ReadEmployeeResponse>(response,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListEmployeeResponse>> getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Employee> employees = this.employeeRepository.findAll(pageable).getContent();
        List<ListEmployeeResponse> responces = employees.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListEmployeeResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListEmployeeResponse>>(responces,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListEmployeeResponse>> getAll(Integer pageNo, Integer pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,Sort.by(field));
        List<Employee> employees = this.employeeRepository.findAll(pageable).getContent();
        List<ListEmployeeResponse> responces = employees.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListEmployeeResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListEmployeeResponse>>(responces,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListEmployeeResponse>> getAll(Integer pageNo, Integer pageSize, String field, boolean isStateDescending) {
        Pageable pageable = checkAscendingOrDescending(pageNo,pageSize,field,isStateDescending);
        List<Employee> employees = this.employeeRepository.findAll(pageable).getContent();
        List<ListEmployeeResponse> responces = employees.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListEmployeeResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListEmployeeResponse>>(responces,"Listed Successfully");
    }

    private Pageable checkAscendingOrDescending(Integer pageNo, Integer pageSize, String field, boolean isStateDescending) {
        Pageable pageable;
        if (isStateDescending) {
            pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(field).descending());
        } else {
            pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(field).ascending());
        }
        return pageable;
    }
    private void checkIEmployeeReportLimitExceeds(int reportsTo) {
        List<Employee> employees = this.employeeRepository.findByReportsTo(reportsTo);
        if (employees.size() >= 10) {
            throw new BusinessException("CHECK REPORTS COUNT'");
        }
    }
}