package tt.authorization.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tt.authorization.dto.UserDto;
import tt.authorization.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('write')")
    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll().stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('write')")
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.findById(id).map(UserDto::fromUser).orElse(null);
    }

    @PreAuthorize("hasAuthority('write')")
    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        userService.save(UserDto.fromUserDto(userDto));
    }

    @PreAuthorize("hasAuthority('write')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PreAuthorize("hasAuthority('check')")
    @PostMapping("/check")
    public UserDto checkUser(Principal principal) {
        return UserDto.fromUser(userService.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Email doesn't exist")));
    }
}
