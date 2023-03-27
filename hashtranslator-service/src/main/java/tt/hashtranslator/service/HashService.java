package tt.hashtranslator.service;

import tt.hashtranslator.model.Hash;

import java.util.List;
import java.util.Optional;

public interface HashService {

    String processHash(Hash hash);

    Optional<Hash> findById(String id);

    List<Hash> findByEmailOwner(String emailOwner);
}
