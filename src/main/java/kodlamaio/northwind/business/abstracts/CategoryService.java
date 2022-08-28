package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.business.requests.categoryRequests.CreateCategoryRequest;
import kodlamaio.northwind.business.requests.categoryRequests.DeleteCategoryRequest;
import kodlamaio.northwind.business.requests.categoryRequests.UpdateCategoryRequest;
import kodlamaio.northwind.business.responses.categories.ListCategoryResponse;
import kodlamaio.northwind.business.responses.categories.ReadCategoryResponse;
import kodlamaio.northwind.business.core.utilities.Results.DataResult;
import kodlamaio.northwind.business.core.utilities.Results.Result;

import java.util.List;

public interface CategoryService {

    Result add(CreateCategoryRequest createCategoryRequest);
    Result update(UpdateCategoryRequest updateCategoryRequest);
    Result delete(DeleteCategoryRequest deleteCategoryRequest);
    DataResult<List<ListCategoryResponse>> getAll();
    DataResult<ReadCategoryResponse> getById(int categoryId);
    DataResult<List<ListCategoryResponse>> getAll(Integer pageNo, Integer pageSize);
    DataResult<List<ListCategoryResponse>> getAll(Integer pageNo, Integer pageSize,String field);
    DataResult<List<ListCategoryResponse>> getAll(Integer pageNo, Integer pageSize,String field,boolean state);



}
