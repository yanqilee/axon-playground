package com.example.demo.controller;

import com.example.demo.command.IssueCmd;
import com.example.demo.command.RedeemCmd;
import com.example.demo.model.CardSummary;
import com.example.demo.service.GiftCardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class GiftCardController {

    private final GiftCardService redeemService;

    public GiftCardController(GiftCardService redeemService) {
        this.redeemService = redeemService;
    }

    @RequestMapping("/issue")
    public String issue() {
        redeemService.issue(new IssueCmd("gc1", 100));
        redeemService.issue(new IssueCmd("gc2", 50));

        return "Success";
    }

    @RequestMapping("/redeem")
    public String redeem() {
        redeemService.redeem(new RedeemCmd("gc1", 10));
        redeemService.redeem(new RedeemCmd("gc2", 20));

        return "Success";
    }

    @RequestMapping("/summary")
    public List<CardSummary> summary() throws ExecutionException, InterruptedException {
        return redeemService.query();
    }
}
