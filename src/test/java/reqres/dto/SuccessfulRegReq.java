package reqres.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@AllArgsConstructor
public class SuccessfulRegReq {
    private String email;
    private String password;
}
