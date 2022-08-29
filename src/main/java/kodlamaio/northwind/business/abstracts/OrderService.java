package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.business.requests.orderRequests.CreateOrderRequest;
import kodlamaio.northwind.business.requests.orderRequests.DeleteOrderRequest;
import kodlamaio.northwind.business.requests.orderRequests.UpdateOrderRequest;
import kodlamaio.northwind.business.responses.orders.ListOrderResponse;
import kodlamaio.northwind.business.responses.orders.ReadOrderResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;

import java.util.List;

public interface OrderService {
    Result add(CreateOrderRequest createOrderRequest);
    Result update(UpdateOrderRequest updateOrderRequest);
    Result delete(DeleteOrderRequest deleteOrderRequest);
    DataResult<List<ListOrderResponse>> getAll();
    DataResult<ReadOrderResponse> getById(int orderId);
    DataResult<List<ListOrderResponse>>  getAll(Integer pageNo, Integer pageSize);
    DataResult<List<ListOrderResponse>>  getAll(Integer pageNo, Integer pageSize,String field);
    DataResult<List<ListOrderResponse>>  getAll(Integer pageNo, Integer pageSize,String field,boolean state);
   }
