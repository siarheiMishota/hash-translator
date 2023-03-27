package tt.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tt.authorization.entity.Role;
import tt.authorization.entity.Status;
import tt.authorization.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public UserDto(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static User fromUserDto(UserDto userDto) {
        return new User(userDto.firstName, userDto.lastName, userDto.email, userDto.password, Role.USER, Status.ACTIVE);
    }

    public static UserDto fromUser(User user) {
        return new UserDto(user.getEmail(), user.getFirstName(), user.getLastName());
    }
}
