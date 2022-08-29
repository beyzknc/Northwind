package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.business.requests.customerRequests.CreateCustomerRequest;
import kodlamaio.northwind.business.requests.customerRequests.DeleteCustomerRequest;
import kodlamaio.northwind.business.requests.customerRequests.UpdateCustomerRequest;
import kodlamaio.northwind.business.responses.customers.ListCustomerResponse;
import kodlamaio.northwind.business.responses.customers.ReadCustomerResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;

import java.util.List;

public interface CustomerService {
    Result add(CreateCustomerRequest createCustomerRequest);
    Result update(UpdateCustomerRequest updateCustomerRequest);
    Result delete(DeleteCustomerRequest deleteCustomerRequest);
    DataResult<List<ListCustomerResponse>> getAll();
    DataResult<ReadCustomerResponse> getById(String customerId);
    DataResult<List<ListCustomerResponse>> getAll(Integer pageNo, Integer pageSize);
    DataResult<List<ListCustomerResponse>> getAll(Integer pageNo, Integer pageSize,String field);
    DataResult<List<ListCustomerResponse>> getAll(Integer pageNo, Integer pageSize,String field,boolean state);

}
