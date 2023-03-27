package tt.hashtranslator.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.hash-translator")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorProperties {
    private String url;
    private String hashType;
    private String email;
    private String code;
}
