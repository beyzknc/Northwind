package kodlamaio.northwind.api.controllers;

import kodlamaio.northwind.business.abstracts.EmployeeService;
import kodlamaio.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import kodlamaio.northwind.business.requests.employeeRequests.DeleteEmployeeRequest;
import kodlamaio.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import kodlamaio.northwind.business.responses.employees.ListEmployeeResponse;
import kodlamaio.northwind.business.responses.employees.ReadEmployeeResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/getall")
    public DataResult<List<ListEmployeeResponse>> getAll(){
        return this.employeeService.getAll();
    }
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest){
        return this.employeeService.add(createEmployeeRequest);
    }
    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
        return this.employeeService.update(updateEmployeeRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@Valid DeleteEmployeeRequest deleteEmployeeRequest){
        return   this.employeeService.delete(deleteEmployeeRequest);
    }
    @GetMapping("/getbyid")
    public DataResult<ReadEmployeeResponse> getById(int  employeeId){
        return this.employeeService.getById(employeeId);
    }
    @GetMapping("/getAllByPage")
    public DataResult<List<ListEmployeeResponse>> GetAll(@RequestParam int pageNo, int pageSize) {
        return employeeService.getAll(pageNo, pageSize);
    }
    @GetMapping("/getAllByPageWithField")
    public DataResult<List<ListEmployeeResponse>> GetAll(@RequestParam int pageNo, int pageSize,String field) {
        return employeeService.getAll(pageNo, pageSize,field);
    }
    @GetMapping("/getAllByPageWithFieldAscOrDesc")
    public DataResult<List<ListEmployeeResponse>> GetAll(@RequestParam int pageNo, int pageSize,String field,boolean state) {
        return employeeService.getAll(pageNo, pageSize,field,state);
    }

}
