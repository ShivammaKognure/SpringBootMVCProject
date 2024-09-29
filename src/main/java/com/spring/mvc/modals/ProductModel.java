package com.spring.mvc.modals;



import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel 
{
	@NotEmpty(message = "Product name is required")
    private String proName;

    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than zero")
    private Double proPrice;

    @NotEmpty(message = "Product description is required")
    private String proDescription;

    @NotEmpty(message = "Product category is required")
    private String proCategory;

    @NotEmpty(message = "Product brand is required")
    private String proBrand;
	
	

}
