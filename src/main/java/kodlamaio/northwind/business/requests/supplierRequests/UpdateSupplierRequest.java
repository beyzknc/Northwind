package kodlamaio.northwind.business.requests.supplierRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierRequest {

    @NotNull
    private int supplierId;
    @NotNull
    private String companyName;
    @NotNull
    private String contactName;
    @NotNull
    private String contactTitle;

}
