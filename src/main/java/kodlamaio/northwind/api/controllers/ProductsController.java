package kodlamaio.northwind.api.controllers;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.business.requests.productRequests.CreateProductRequest;
import kodlamaio.northwind.business.requests.productRequests.DeleteProductRequest;
import kodlamaio.northwind.business.requests.productRequests.UpdateProductRequest;
import kodlamaio.northwind.business.responses.products.ListProductResponse;
import kodlamaio.northwind.business.responses.products.ReadProductResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private ProductService productService;
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateProductRequest createProductRequest){
        return this.productService.add(createProductRequest);
    }
    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateProductRequest updateProductRequest){
        return this.productService.update(updateProductRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@Valid DeleteProductRequest deleteProductRequest){
        return this.productService.delete(deleteProductRequest);
    }
    @GetMapping("/getall")
    public DataResult<List<ListProductResponse>> getAll(){
        return this.productService.getAll();
    }
    @GetMapping("/getbyid")
    public DataResult<ReadProductResponse> getById( int productId){
        return this.productService.getById(productId);
    }
    @GetMapping("/getAllByPage")
    public DataResult<List<ListProductResponse>> GetAll(@RequestParam int pageNo, int pageSize) {
        return productService.getAll(pageNo, pageSize);
    }
    @GetMapping("/getAllByPageWithField")
    public DataResult<List<ListProductResponse>>  GetAll(@RequestParam int pageNo, int pageSize,String field) {
        return productService.getAll(pageNo, pageSize,field);
    }
    @GetMapping("/getAllByPageWithFieldAscOrDesc")
    public DataResult<List<ListProductResponse>> GetAll(@RequestParam int pageNo, int pageSize,String field,boolean state) {
        return productService.getAll(pageNo, pageSize,field,state);
    }
}
