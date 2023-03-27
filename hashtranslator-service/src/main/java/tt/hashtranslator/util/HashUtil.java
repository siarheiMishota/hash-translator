package tt.hashtranslator.util;

import tt.hashtranslator.dto.HashDto;
import tt.hashtranslator.dto.HashResponseDto;
import tt.hashtranslator.model.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class HashUtil {
    private HashUtil() {
    }

    public static Hash fromHashDto(HashDto hashDto, String emailOwner) {
        HashMap<String, String> map = hashDto.getHashes().stream().collect(Collectors.toMap(hash -> hash, hash -> "", (a, b) -> b, HashMap::new));
        return new Hash(map, emailOwner, false);
    }

    public static HashDto fromHash(Hash hash) {
        return new HashDto(new ArrayList<>(hash.getHashes().keySet()));
    }

    public static HashResponseDto hashResponseDtoFromHash(Hash hash) {
        return new HashResponseDto(hash.getHashes());
    }
}
