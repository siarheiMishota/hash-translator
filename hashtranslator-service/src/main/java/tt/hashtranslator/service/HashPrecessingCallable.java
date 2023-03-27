package tt.hashtranslator.service;

import tt.hashtranslator.model.Hash;
import tt.hashtranslator.repository.HashRepository;

import java.util.Map;

public class HashPrecessingCallable implements Runnable {

    private final Hash hash;
    private final HashTranslator hashTranslator;
    private final HashRepository hashRepository;

    public HashPrecessingCallable(Hash hash, HashTranslator hashTranslator, HashRepository hashRepository) {
        this.hash = hash;
        this.hashTranslator = hashTranslator;
        this.hashRepository = hashRepository;
    }

    @Override
    public void run() {
        Map<String, String> hashMap = hashTranslator.translateAll(hash.getHashes().keySet());
        hash.setHashes(hashMap);
        hash.setProcessed(true);
        hashRepository.save(hash);
    }
}
