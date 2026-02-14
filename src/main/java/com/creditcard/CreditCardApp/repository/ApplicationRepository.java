package com.creditcard.CreditCardApp.repository;

import com.creditcard.CreditCardApp.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ApplicationRepository
        extends MongoRepository<Application, String> {

    Optional<Application> findByPanNumber(String panNumber);
}
