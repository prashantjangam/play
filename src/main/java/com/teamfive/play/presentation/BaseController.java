package com.teamfive.play.presentation;

import com.teamfive.play.Messages;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;


public class BaseController implements Serializable {

    @Autowired
    Messages messages;

    public String sucessfull() {
        return messages.get("save.sucessfull");
    }

    public String unsucessfull() {
        return messages.get("save.unsucessfull");
    }

    public String deleteSucessfull() {
        return messages.get("delete.sucessfull");
    }

    public String deleteUnSucessfull() {
        return messages.get("delete.unsucessfull");
    }

    public String dataNotFound() {
        return messages.get("data.not.found");
    }

}
