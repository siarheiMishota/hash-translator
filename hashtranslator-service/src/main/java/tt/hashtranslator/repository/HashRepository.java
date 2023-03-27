package tt.hashtranslator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tt.hashtranslator.model.Hash;

import java.util.List;
import java.util.Optional;

public interface HashRepository extends MongoRepository<Hash, String> {
    @Query("{_id:'?0'}")
    Optional<Hash> findById(String id);

    @Query("{emailOwner:'?0'}")
    List<Hash> findByEmailOwner(String emailOwner);
}
