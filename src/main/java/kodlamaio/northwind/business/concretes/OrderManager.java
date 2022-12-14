package kodlamaio.northwind.business.concretes;

import kodlamaio.northwind.business.abstracts.OrderService;
import kodlamaio.northwind.business.requests.orderRequests.CreateOrderRequest;
import kodlamaio.northwind.business.requests.orderRequests.DeleteOrderRequest;
import kodlamaio.northwind.business.requests.orderRequests.UpdateOrderRequest;
import kodlamaio.northwind.business.responses.orders.ListOrderResponse;
import kodlamaio.northwind.business.responses.orders.ReadOrderResponse;
import kodlamaio.northwind.core.utilities.mapping.ModelMapperService;
import kodlamaio.northwind.core.utilities.Results.DataResult;
import kodlamaio.northwind.core.utilities.Results.Result;
import kodlamaio.northwind.core.utilities.Results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.Results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.OrderRepository;
import kodlamaio.northwind.entities.concretes.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public Result add(CreateOrderRequest createOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
        orderRepository.save(order);
        return new SuccessResult("Added successfully");
    }

    @Override
    public Result update(UpdateOrderRequest updateOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(updateOrderRequest, Order.class);
        orderRepository.save(order);
        return new SuccessResult("Updated successfully");
    }

    @Override
    public Result delete(DeleteOrderRequest deleteOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(deleteOrderRequest, Order.class);
        this.orderRepository.deleteById(order.getOrderId());
        return new SuccessResult("Deleted successfully");
    }

    @Override
    public DataResult<List<ListOrderResponse>> getAll() {
        List<Order> result = this.orderRepository.findAll();
        List<ListOrderResponse> response =
                result.stream().map(item -> this.modelMapperService.forResponse().map(item, ListOrderResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListOrderResponse>>(response,"Listed Successfully");
    }

    @Override
    public DataResult<ReadOrderResponse>  getById( int orderId) {
        Order order=this.orderRepository.findById(orderId).get();
        ReadOrderResponse response=modelMapperService.forResponse().map(order, ReadOrderResponse.class);
        return new SuccessDataResult<ReadOrderResponse>(response,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListOrderResponse>> getAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Order> categories = this.orderRepository.findAll(pageable).getContent();
        List<ListOrderResponse> responces = categories.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListOrderResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListOrderResponse>>(responces,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListOrderResponse>> getAll(Integer pageNo, Integer pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,Sort.by(field));
        List<Order> categories = this.orderRepository.findAll(pageable).getContent();
        List<ListOrderResponse> responces = categories.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListOrderResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListOrderResponse>>(responces,"Listed Successfully");
    }

    @Override
    public DataResult<List<ListOrderResponse>> getAll(Integer pageNo, Integer pageSize, String field, boolean isStateDescending) {
        Pageable pageable = checkAscendingOrDescending(pageNo,pageSize,field,isStateDescending);
        List<Order> categories = this.orderRepository.findAll(pageable).getContent();
        List<ListOrderResponse> responces = categories.stream()
                .map(item -> modelMapperService.forResponse().map(item, ListOrderResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListOrderResponse>>(responces,"Listed Successfully");
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

