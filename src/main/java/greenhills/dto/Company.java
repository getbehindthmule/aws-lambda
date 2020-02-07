package greenhills.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Company {
    private Integer id ;
    private String name;
}
