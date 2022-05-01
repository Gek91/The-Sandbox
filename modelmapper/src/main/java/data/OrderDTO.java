package data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
	private String customerFirstName;
	private String customerLastName;
	private String billingStreet;
	private String billingCity;
	private DataDTO data;
}
