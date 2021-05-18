package com.l1keacat.codeplatform.controller;

import com.l1keacat.codeplatform.logic.CodeReader;
import com.l1keacat.codeplatform.model.HistoryItem;
import com.l1keacat.codeplatform.service.HistoryService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    CodeReader codeReader = new CodeReader();

    @Autowired
    private HistoryService historyService;

    @GetMapping("/decode/{user}/{code}")
    public ResponseEntity<String> getInfoByCode(@PathVariable("code") String code, @PathVariable("user") String user) {
        if (!code.equals("null")) {
            String data = codeReader.getInfoByCode(code);

            JSONObject json =  new JSONObject(data);
            JSONArray products = (JSONArray) json.get("products");
            if(!products.getJSONObject(0).has("error")) {
                HistoryItem item = this.historyService.findByUserEmailAndContent(user, code);
                item.setStatus("recognized");
                this.historyService.add(item);
            }

            System.out.println(data);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/uploadFile/{user}")
    public ResponseEntity<String> createCourse(@RequestParam("file") MultipartFile file, @PathVariable("user") String user) {
        String message;
        try {
            File newFile = File.createTempFile(UUID.randomUUID().toString(), "tmp");
            file.transferTo(newFile);
            String response = codeReader.decodeCode(newFile);
            newFile.deleteOnExit();

            JSONObject json =  new JSONObject(response);

            HistoryItem historyItem = new HistoryItem();
            historyItem.setUserEmail(user);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            historyItem.setDatetime(formatter.format(date));
            historyItem.setType_format(json.get("type_format").toString());
            if (json.get("type_dimension").toString().equals("1D"))
                historyItem.setStatus("decoded");
            else
                historyItem.setStatus("recognized");
            historyItem.setType_dimension(json.get("type_dimension").toString());
            historyItem.setContent(json.get("content").toString());
            this.historyService.add(historyItem);

            System.out.println(response);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/history/{user}")
    public ResponseEntity<List<HistoryItem>> getHistoryByUser(@PathVariable("user") String user) {
        if (!user.equals("undefined")) {
            List<HistoryItem> history = this.historyService.getAllByUser(user);
            return new ResponseEntity<>(history, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
