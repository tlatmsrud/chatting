package org.ssk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * title        :
 * author       : sim
 * date         : 2023-07-20
 * description  :
 */

@Controller
public class ViewController {

    @GetMapping("/chat")
    public String getChatPage(){
        return "chat.html";
    }

    @GetMapping("/list")
    public String getMemberPage(){
        return "list.html";
    }
}
