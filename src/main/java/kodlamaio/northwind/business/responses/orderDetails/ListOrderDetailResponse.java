package kodlamaio.northwind.business.responses.orderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderDetailResponse {

    private int orderId;
    private int productId;
    private String productName;
    private double unitPrice;
    private int quantity;
    private double discount;

}
