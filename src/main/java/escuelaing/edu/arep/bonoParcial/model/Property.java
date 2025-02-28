package escuelaing.edu.arep.bonoParcial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class Property {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    private Long id;
    private String address;
    private double price;
    private int size;
    private String description;

    public Property(String address, double price, int size, String description){
        this.address = address;
        this.description = description;
        this.price = price;
        this.size = size;
    }
}
