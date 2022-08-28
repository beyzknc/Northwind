package kodlamaio.northwind.dataAccess.abstracts;

import kodlamaio.northwind.entities.concretes.OrderDetail;
import kodlamaio.northwind.entities.concretes.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, OrderDetailId> {

}
