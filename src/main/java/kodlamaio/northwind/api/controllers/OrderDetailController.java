package kodlamaio.northwind.api.controllers;

import kodlamaio.northwind.business.abstracts.OrderDetailService;
import kodlamaio.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import kodlamaio.northwind.business.requests.orderDetailRequests.DeleteOrderDetailRequest;
import kodlamaio.northwind.business.requests.orderDetailRequests.UpdateOrderDetailRequest;
import kodlamaio.northwind.business.responses.orderDetails.ListOrderDetailResponse;
import kodlamaio.northwind.business.responses.orderDetails.ReadOrderDetailResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailController {
    private OrderDetailService orderDetailService;
    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateOrderDetailRequest createOrderDetailRequest){
        return  this.orderDetailService.add(createOrderDetailRequest);
    }
    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateOrderDetailRequest updateOrderDetailRequest){
        return this.orderDetailService.update(updateOrderDetailRequest);
    }
    @DeleteMapping("/delete")
    public Result delete(@Valid DeleteOrderDetailRequest deleteOrderDetailRequest){
        return this.orderDetailService.delete(deleteOrderDetailRequest);
    }
    @GetMapping("/getall")
    public DataResult<List<ListOrderDetailResponse>> getAll(){
        return this.orderDetailService.getAll();
    }

    @GetMapping("/getbyid")
    public DataResult<ReadOrderDetailResponse> getById(int orderId, int productId){
        return this.orderDetailService.getById(orderId,productId);
    }
    @GetMapping("/getAllByPage")
    public DataResult<List<ListOrderDetailResponse>> GetAll(@RequestParam int pageNo, int pageSize) {
        return orderDetailService.getAll(pageNo, pageSize);
    }

    @GetMapping("/getAllByPageWithField")
    public DataResult<List<ListOrderDetailResponse>> GetAll(@RequestParam int pageNo, int pageSize,String field) {
        return orderDetailService.getAll(pageNo, pageSize,field);
    }
    @GetMapping("/getAllByPageWithFieldAscOrDesc")
    public DataResult<List<ListOrderDetailResponse>> GetAll(@RequestParam int pageNo, int pageSize,String field,boolean state) {
        return orderDetailService.getAll(pageNo, pageSize,field,state);
    }
}
