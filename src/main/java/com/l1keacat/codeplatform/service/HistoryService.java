package com.l1keacat.codeplatform.service;

import com.l1keacat.codeplatform.model.HistoryItem;
import com.l1keacat.codeplatform.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public void add(HistoryItem item) {
        historyRepository.save(item);
    }

    public HistoryItem findByUserEmailAndContent(String userEmail, String content) {
        int size = historyRepository.findByUserEmailAndContent(userEmail, content).size();
        return historyRepository.findByUserEmailAndContent(userEmail, content).get(size - 1);
    }

    public List<HistoryItem> getAllByUser(String userEmail) {
        return historyRepository.findByUserEmail(userEmail);
    }

    public List<HistoryItem> getAll() {
        return historyRepository.findAll();
    }

    public void remove(String userEmail, String content) {
        List<HistoryItem> history = historyRepository.findByUserEmailAndContent(userEmail, content);
        history.forEach(historyItem -> historyRepository.delete(historyItem));
    }
}
