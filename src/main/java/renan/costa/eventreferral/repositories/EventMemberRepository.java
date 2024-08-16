package renan.costa.eventreferral.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import renan.costa.eventreferral.entities.EventMemberEntity;

import java.util.Optional;

public interface EventMemberRepository extends MongoRepository<EventMemberEntity, String> {
    Optional<EventMemberEntity> findByReferralId(String referralId);
}
