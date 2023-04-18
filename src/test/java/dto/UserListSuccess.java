package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class UserListSuccess {
    private List<User> data;
    private int total;
    private int page;
    private int limit;
}
