package com.kekwy.mcolc.repo;

import com.kekwy.mcolc.model.PlayerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDetailsRepository extends MongoRepository<PlayerDetails, String> {
}
