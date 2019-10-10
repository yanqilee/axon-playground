package com.example.demo.controller;

import com.example.demo.command.IssueCmd;
import com.example.demo.command.RedeemCmd;
import com.example.demo.model.CardSummary;
import com.example.demo.service.GiftCardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
public class GiftCardController {

    private final GiftCardService redeemService;

    public GiftCardController(GiftCardService redeemService) {
        this.redeemService = redeemService;
    }

    @GetMapping("/issue/{amount}")
    public IssueCmd issue(@PathVariable(value = "amount") Integer amount) {
        IssueCmd issueCmd = new IssueCmd(UUID.randomUUID().toString(), amount);
        redeemService.issue(issueCmd);

        return issueCmd;
    }

    @GetMapping("/redeem/{id}/{amount}")
    public RedeemCmd redeem(@PathVariable(value = "id") String id, @PathVariable(value = "amount") Integer amount) {
        RedeemCmd redeemCmd = new RedeemCmd(id, amount);
        redeemService.redeem(redeemCmd);

        return redeemCmd;
    }

    @GetMapping("/summary")
    public List<CardSummary> summary() throws ExecutionException, InterruptedException {
        return redeemService.query();
    }
}
