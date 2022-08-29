package kodlamaio.northwind.business.concretes;


import kodlamaio.northwind.business.abstracts.CustomerService;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;
import kodlamaio.northwind.core.utilities.Results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.Results.SuccessResult;
import kodlamaio.northwind.core.utilities.mapping.ModelMapperService;
import kodlamaio.northwind.business.requests.customerRequests.CreateCustomerRequest;
import kodlamaio.northwind.business.requests.customerRequests.DeleteCustomerRequest;
import kodlamaio.northwind.business.requests.customerRequests.UpdateCustomerRequest;
import kodlamaio.northwind.business.responses.customers.ListCustomerResponse;
import kodlamaio.northwind.business.responses.customers.ReadCustomerResponse;
import kodlamaio.northwind.dataAccess.abstracts.CustomerRepository;
import kodlamaio.northwind.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public Result add(CreateCustomerRequest createCustomerRequest) {
        Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        customerRepository.save(customer);
        return new SuccessResult("Added successfully");
    }

    @Override
    public Result update(UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
        customerRepository.save(customer);
        return new SuccessResult("Updated successfully");
    }

    @Override
    public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
        Customer customer = this.modelMapperService.forRequest().map(deleteCustomerRequest, Customer.class);
        this.customerRepository.deleteById(customer.getCustomerId());
        return new SuccessResult("Deleted successfully");
    }

    @Override
    public DataResult<List<ListCustomerResponse>> getAll() {
        List<Customer> result = this.customerRepository.findAll();
        List<ListCustomerResponse> response =
                result.stream().map(customer -> this.modelMapperService.forResponse().map(customer, ListCustomerResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCustomerResponse>>(response,"Listed Successfully");

    }

    @Override
    public DataResult<ReadCustomerResponse> getById( String customerId) {
        Customer customer= this.customerRepository.findById(customerId).get();
        ReadCustomerResponse response = modelMapperService.forResponse().map(customer, ReadCustomerResponse.class);
        return new SuccessDataResult<ReadCustomerResponse>(response,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListCustomerResponse>> getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Customer> customers = this.customerRepository.findAll(pageable).getContent();
        List<ListCustomerResponse> responces = customers.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListCustomerResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCustomerResponse>>(responces,"Listed Successfully");

    }

    @Override
    public DataResult<List<ListCustomerResponse>> getAll(Integer pageNo, Integer pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,Sort.by(field));
        List<Customer> customers = this.customerRepository.findAll(pageable).getContent();
        List<ListCustomerResponse> responces = customers.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListCustomerResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCustomerResponse>>(responces,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListCustomerResponse>> getAll(Integer pageNo, Integer pageSize, String field, boolean isStateDescending) {
        Pageable pageable = checkAscendingOrDescending(pageNo,pageSize,field,isStateDescending);
        List<Customer> customers = this.customerRepository.findAll(pageable).getContent();
        List<ListCustomerResponse> responces = customers.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListCustomerResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCustomerResponse>>(responces,"Listed Successfully");
    }
    private Pageable checkAscendingOrDescending(Integer pageNo, Integer pageSize, String field, boolean isStateDescending)
    {
        Pageable pageable;
        if(isStateDescending)
        {
            pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(field).descending());
        }
        else
        {
            pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(field).ascending());
        }
        return pageable;
    }

}
