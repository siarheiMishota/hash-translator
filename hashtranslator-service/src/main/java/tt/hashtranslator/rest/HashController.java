package tt.hashtranslator.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tt.hashtranslator.dto.HashDto;
import tt.hashtranslator.dto.HashResponseDto;
import tt.hashtranslator.dto.UserDto;
import tt.hashtranslator.model.Hash;
import tt.hashtranslator.service.CheckingAuth;
import tt.hashtranslator.service.HashService;
import tt.hashtranslator.util.HashUtil;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static tt.hashtranslator.util.HashUtil.hashResponseDtoFromHash;

@Slf4j
@RestController
@RequestMapping("/api/applications")
public class HashController {

    private final CheckingAuth checkingAuth;
    private final HashService hashService;

    public HashController(CheckingAuth checkingAuth, HashService hashService) {
        this.checkingAuth = checkingAuth;
        this.hashService = hashService;
    }

    @PostMapping
    public ResponseEntity<String> processHashes(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody HashDto hashDto) {
        UserDto userDto = checkingAuth.checkUserIn(authorization);

        if (userDto == null) {
            return new ResponseEntity<>("User doesn't exist with specified email", UNAUTHORIZED);
        }

        String id = hashService.processHash(HashUtil.fromHashDto(hashDto, userDto.getEmail()));

        return new ResponseEntity<>(id, ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashResponseDto> getHashes(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable String id) {
        UserDto userDto = checkingAuth.checkUserIn(authorization);

        if (userDto == null) {
            return new ResponseEntity<>(null, UNAUTHORIZED);
        }

        Optional<Hash> optionalHash = hashService.findById(id);

        if (!optionalHash.isPresent()) {
            return new ResponseEntity<>(null, NOT_FOUND);
        }
        Hash hash = optionalHash.get();
        if (!userDto.getEmail().equalsIgnoreCase(hash.getEmailOwner())) {
            return new ResponseEntity<>(null, NOT_ACCEPTABLE);
        }

        if (!hash.isProcessed()) {
            return new ResponseEntity<>(null, NOT_FOUND);
        }

        return new ResponseEntity<>(hashResponseDtoFromHash(hash), OK);
    }
}
