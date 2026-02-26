package com.creditcard.CreditCardApp.repository;

import com.creditcard.CreditCardApp.model.Application;
import com.creditcard.CreditCardApp.model.ApplicationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository
        extends MongoRepository<Application, String> {

    Optional<Application> findByPanNumber(String panNumber);
    List<Application> findByStatus(ApplicationStatus status);
}
