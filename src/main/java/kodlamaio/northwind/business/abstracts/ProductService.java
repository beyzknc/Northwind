package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.business.requests.productRequests.CreateProductRequest;
import kodlamaio.northwind.business.requests.productRequests.DeleteProductRequest;
import kodlamaio.northwind.business.requests.productRequests.UpdateProductRequest;
import kodlamaio.northwind.business.responses.products.ListProductResponse;
import kodlamaio.northwind.business.responses.products.ReadProductResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;

import java.util.List;

public interface ProductService {
    Result add(CreateProductRequest createProductRequest);
    Result update(UpdateProductRequest updateProductRequest);
    Result delete(DeleteProductRequest deleteProductRequest);
    DataResult<List<ListProductResponse>> getAll();
    DataResult<ReadProductResponse>  getById(int productId);
    DataResult<List<ListProductResponse>> getAll(Integer pageNo, Integer pageSize);
    DataResult<List<ListProductResponse>> getAll(Integer pageNo, Integer pageSize,String field);
    DataResult<List<ListProductResponse>> getAll(Integer pageNo, Integer pageSize,String field,boolean state);
}
