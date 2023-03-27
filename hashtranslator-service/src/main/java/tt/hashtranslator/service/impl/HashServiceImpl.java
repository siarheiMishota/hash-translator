package tt.hashtranslator.service.impl;

import org.springframework.stereotype.Service;
import tt.hashtranslator.model.Hash;
import tt.hashtranslator.repository.HashRepository;
import tt.hashtranslator.service.HashPrecessingCallable;
import tt.hashtranslator.service.HashService;
import tt.hashtranslator.service.HashTranslator;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

@Service
public class HashServiceImpl implements HashService {
    private final HashRepository hashRepository;
    private final HashTranslator hashTranslator;
    private final ExecutorService executorService;

    public HashServiceImpl(HashRepository hashRepository, HashTranslator hashTranslator, ExecutorService executorService) {
        this.hashRepository = hashRepository;
        this.hashTranslator = hashTranslator;
        this.executorService = executorService;
    }

    @Override
    public String processHash(Hash hash) {
        Hash save = hashRepository.save(hash);
        executorService.submit(new HashPrecessingCallable(hash, hashTranslator, hashRepository));
        return save.getId();
    }

    @Override
    public Optional<Hash> findById(String id) {
        return hashRepository.findById(id);
    }

    @Override
    public List<Hash> findByEmailOwner(String emailOwner) {
        return hashRepository.findByEmailOwner(emailOwner);
    }
}
