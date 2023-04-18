package reqres.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserData {

    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
