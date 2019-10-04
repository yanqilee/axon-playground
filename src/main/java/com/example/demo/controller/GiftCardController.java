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
import java.util.concurrent.ExecutionException;

@RestController
public class GiftCardController {

    private final GiftCardService redeemService;

    public GiftCardController(GiftCardService redeemService) {
        this.redeemService = redeemService;
    }

    @GetMapping("/issue/{amount}")
    public IssueCmd issue(@PathVariable(value = "amount") Integer amount) {
        IdGenerator.increment();
        IssueCmd issueCmd = new IssueCmd(Integer.toString(IdGenerator.getId()), amount);
        redeemService.issue(issueCmd);

        return issueCmd;
    }

    @RequestMapping("/redeem/{id}/{amount}")
    public RedeemCmd redeem(@PathVariable(value = "id") Integer id, @PathVariable(value = "amount") Integer amount) {
        RedeemCmd redeemCmd = new RedeemCmd(Integer.toString(id), amount);
        redeemService.redeem(redeemCmd);

        return redeemCmd;
    }

    @RequestMapping("/summary")
    public List<CardSummary> summary() throws ExecutionException, InterruptedException {
        return redeemService.query();
    }
}
