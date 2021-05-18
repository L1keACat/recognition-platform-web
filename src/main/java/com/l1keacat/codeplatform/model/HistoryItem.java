package com.l1keacat.codeplatform.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_history")
public @Data
class HistoryItem {

    @Id
    String id;
    String userEmail;
    String status;
    String datetime;
    String type_format;
    String type_dimension;
    String content;

}
