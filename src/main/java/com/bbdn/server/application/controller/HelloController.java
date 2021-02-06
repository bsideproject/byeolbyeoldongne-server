package com.bbdn.server.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
@Slf4j
public class HelloController {

    @RequestMapping("/")
    public String hello(Model model) {

        String	hostName;
        String	memorySize;

        try {
            hostName = InetAddress.getLocalHost().getHostName();
        }catch(Exception e) {
            hostName = "FAIL TO GET HOSTNAME";
        }

        memorySize = String.format("%d mb", (Runtime.getRuntime().totalMemory() / (1024 * 1024)));

        model.addAttribute("hostName", hostName);
        model.addAttribute("memory", memorySize);

        return hostName.toString() + " " + memorySize.toString();
    }
}
