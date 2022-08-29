package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.northwind.business.requests.employeeRequests.DeleteEmployeeRequest;
import kodlamaio.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import kodlamaio.northwind.business.responses.employees.ListEmployeeResponse;
import kodlamaio.northwind.business.responses.employees.ReadEmployeeResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;

import java.util.List;

public interface EmployeeService {

    Result add(CreateEmployeeRequest createEmployeeRequest);
    Result update(UpdateEmployeeRequest updateEmployeeRequest);
    Result delete(DeleteEmployeeRequest deleteEmployeeRequest);
    DataResult<List<ListEmployeeResponse>> getAll();
    DataResult<ReadEmployeeResponse>  getById(int employeeId);
    DataResult<List<ListEmployeeResponse>> getAll(Integer pageNo, Integer pageSize);
    DataResult<List<ListEmployeeResponse>> getAll(Integer pageNo, Integer pageSize,String field);
    DataResult<List<ListEmployeeResponse>> getAll(Integer pageNo, Integer pageSize,String field,boolean state);


}
