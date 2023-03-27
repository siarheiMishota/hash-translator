package tt.hashtranslator.service;

import tt.hashtranslator.dto.UserDto;

public interface CheckingAuth {
    UserDto checkUserIn(String authorization);
}
