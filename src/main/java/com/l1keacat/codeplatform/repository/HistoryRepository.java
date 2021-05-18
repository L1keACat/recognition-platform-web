package com.l1keacat.codeplatform.repository;

import com.l1keacat.codeplatform.model.HistoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoryRepository extends MongoRepository<HistoryItem, String> {

    List<HistoryItem> findByUserEmail(String user_email);
    List<HistoryItem> findByUserEmailAndContent(String user_email, String content);

    @Override
    void delete(HistoryItem deleted);
}
