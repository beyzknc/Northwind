package kodlamaio.northwind.business.abstracts;

import kodlamaio.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import kodlamaio.northwind.business.requests.orderDetailRequests.DeleteOrderDetailRequest;
import kodlamaio.northwind.business.requests.orderDetailRequests.UpdateOrderDetailRequest;
import kodlamaio.northwind.business.responses.orderDetails.ListOrderDetailResponse;
import kodlamaio.northwind.business.responses.orderDetails.ReadOrderDetailResponse;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;

import java.util.List;

public interface OrderDetailService {
    Result add(CreateOrderDetailRequest createOrderDetailRequest);
    Result update(UpdateOrderDetailRequest updateOrderDetailRequest);
    Result delete(DeleteOrderDetailRequest deleteOrderDetailRequest);
    DataResult<List<ListOrderDetailResponse>> getAll();
    DataResult<ReadOrderDetailResponse>  getById(int orderId, int productId);
    DataResult<List<ListOrderDetailResponse>> getAll(Integer pageNo, Integer pageSize);
    DataResult<List<ListOrderDetailResponse>> getAll(Integer pageNo, Integer pageSize,String field);
    DataResult<List<ListOrderDetailResponse>> getAll(Integer pageNo, Integer pageSize,String field,boolean state);
}
