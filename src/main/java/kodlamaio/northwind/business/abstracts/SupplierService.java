package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.business.requests.supplierRequests.CreateSupplierRequest;
import kodlamaio.northwind.business.requests.supplierRequests.DeleteSupplierRequest;
import kodlamaio.northwind.business.requests.supplierRequests.UpdateSupplierRequest;
import kodlamaio.northwind.business.responses.suppliers.ListSupplierResponse;
import kodlamaio.northwind.business.responses.suppliers.ReadSupplierResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;

import java.util.List;

public interface SupplierService {
    Result add(CreateSupplierRequest createSupplierRequest);
    Result update(UpdateSupplierRequest updateSupplierRequest);
    Result delete(DeleteSupplierRequest deleteSupplierRequest);
    DataResult<List<ListSupplierResponse>> getAll();
    DataResult<ReadSupplierResponse>  getById(int supplierId);
    DataResult<List<ListSupplierResponse>> getAll(Integer pageNo, Integer pageSize);
    DataResult<List<ListSupplierResponse>>getAll(Integer pageNo, Integer pageSize,String field);
    DataResult<List<ListSupplierResponse>> getAll(Integer pageNo, Integer pageSize,String field,boolean state);
}
