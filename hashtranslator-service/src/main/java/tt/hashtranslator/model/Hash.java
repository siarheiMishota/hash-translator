package tt.hashtranslator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("hashes")
public class Hash {
    @Id
    private String id;
    private Map<String, String> hashes;

    private String emailOwner;
    private boolean processed;

    public Hash(Map<String, String> hashes, String emailOwner, boolean processed) {
        this.hashes = hashes;
        this.emailOwner = emailOwner;
        this.processed = processed;
    }
}
