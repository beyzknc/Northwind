package kodlamaio.northwind.business.concretes;

import kodlamaio.northwind.business.abstracts.SupplierService;
import kodlamaio.northwind.business.requests.supplierRequests.CreateSupplierRequest;
import kodlamaio.northwind.business.requests.supplierRequests.DeleteSupplierRequest;
import kodlamaio.northwind.business.requests.supplierRequests.UpdateSupplierRequest;
import kodlamaio.northwind.business.responses.suppliers.ListSupplierResponse;
import kodlamaio.northwind.business.responses.suppliers.ReadSupplierResponse;
import kodlamaio.northwind.core.utilities.mapping.ModelMapperService;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;
import kodlamaio.northwind.core.utilities.Results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.Results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.SupplierRepository;
import kodlamaio.northwind.entities.concretes.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierManager implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public Result add(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier = this.modelMapperService.forRequest().map(createSupplierRequest, Supplier.class);
        supplierRepository.save(supplier);
        return new SuccessResult("Added successfully");

    }

    @Override
    public Result update(UpdateSupplierRequest updateSupplierRequest) {
        Supplier supplier = this.modelMapperService.forRequest().map(updateSupplierRequest, Supplier.class);
        supplierRepository.save(supplier);
        return new SuccessResult("Updated successfully");
    }

    @Override
    public Result delete(DeleteSupplierRequest deleteSupplierRequest) {
        Supplier supplier = this.modelMapperService.forRequest().map(deleteSupplierRequest, Supplier.class);
        this.supplierRepository.deleteById(supplier.getSupplierId());
        return new SuccessResult("Deleted successfully");
    }

    @Override
    public DataResult<List<ListSupplierResponse>> getAll() {
        List<Supplier> result = this.supplierRepository.findAll();
        List<ListSupplierResponse> response =
                result.stream().map(item -> this.modelMapperService.forResponse().map(item, ListSupplierResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListSupplierResponse>>(response,"Listed Successfully");
    }

    @Override
    public DataResult<ReadSupplierResponse> getById( int supplierId) {
        Supplier supplier=this.supplierRepository.findById(supplierId).get();
        ReadSupplierResponse response=modelMapperService.forResponse().map(supplier, ReadSupplierResponse.class);
        return new SuccessDataResult<ReadSupplierResponse>(response,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListSupplierResponse>> getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Supplier> suppliers = this.supplierRepository.findAll(pageable).getContent();
        List<ListSupplierResponse> responces = suppliers.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListSupplierResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListSupplierResponse>>(responces,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListSupplierResponse>> getAll(Integer pageNo, Integer pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,Sort.by(field));
        List<Supplier> suppliers = this.supplierRepository.findAll(pageable).getContent();
        List<ListSupplierResponse> responces = suppliers.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListSupplierResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListSupplierResponse>>(responces,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListSupplierResponse>> getAll(Integer pageNo, Integer pageSize, String field, boolean isStateDescending) {
        Pageable pageable = checkAscendingOrDescending(pageNo,pageSize,field,isStateDescending);
        List<Supplier> suppliers = this.supplierRepository.findAll(pageable).getContent();
        List<ListSupplierResponse> responces = suppliers.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListSupplierResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListSupplierResponse>>(responces,"Listed Successfully");
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

