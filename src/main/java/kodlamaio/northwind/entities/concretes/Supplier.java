package kodlamaio.northwind.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="supplier_id")
        private int supplierId;

        @Column(name="company_name")
        private String companyName;

        @Column(name="contact_name")
        private String contactName;

        @Column(name="contact_title")
        private String contactTitle;
}
