package com.example.demo.service;

import com.example.demo.command.IssueCmd;
import com.example.demo.command.RedeemCmd;
import com.example.demo.model.CardSummary;
import com.example.demo.query.FetchCardSummariesQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GiftCardService {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public GiftCardService(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public String redeem(RedeemCmd redeemCmd) {
        return commandGateway.sendAndWait(redeemCmd);
    }

    public String issue(IssueCmd issueCmd) {
        return commandGateway.sendAndWait(issueCmd);
    }

    public List<CardSummary> query() throws ExecutionException, InterruptedException {
        return queryGateway.query(new FetchCardSummariesQuery(2, 0),
                ResponseTypes.multipleInstancesOf(CardSummary.class)).get();
    }
}
