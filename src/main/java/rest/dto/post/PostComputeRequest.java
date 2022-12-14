package rest.dto.post;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import rest.dto.Operation;

@Data
@Wither
@AllArgsConstructor
@NoArgsConstructor
public class PostComputeRequest {
    Object val1;
    Object val2;
    Operation operation;
}
