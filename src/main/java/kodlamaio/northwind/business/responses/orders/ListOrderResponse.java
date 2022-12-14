package kodlamaio.northwind.business.responses.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderResponse {

    @NotNull
    private int orderId;
    @NotNull
    private String customerId;
    @NotNull
    private int employeeId;
    @NotNull
    @FutureOrPresent
    private LocalDate orderDate;
}
