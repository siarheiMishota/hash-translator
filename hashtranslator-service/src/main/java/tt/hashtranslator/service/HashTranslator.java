package tt.hashtranslator.service;

import java.util.Collection;
import java.util.Map;

public interface HashTranslator {
    Map<String, String> translateAll(Collection<String> hashes);

    String translate(String hash);
}
