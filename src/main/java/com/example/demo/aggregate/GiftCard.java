package com.example.demo.aggregate;

import com.example.demo.command.IssueCmd;
import com.example.demo.command.RedeemCmd;
import com.example.demo.event.IssuedEvt;
import com.example.demo.event.RedeemedEvt;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class GiftCard {

    @AggregateIdentifier // (1)
    private String id;
    private int remainingValue;

    public GiftCard() {
        // (2)
    }

    @CommandHandler // (3)
    public GiftCard(IssueCmd cmd) {
        if (cmd.getAmount() <= 0) throw new IllegalArgumentException("amount <= 0");
        AggregateLifecycle.apply(new IssuedEvt(cmd.getId(), cmd.getAmount())); // (4)
    }

    @EventSourcingHandler // (5)
    public void on(IssuedEvt evt) {
        id = evt.getId();
        remainingValue = evt.getAmount();
    }

    @CommandHandler
    public void handle(RedeemCmd cmd) {
        if (cmd.getAmount() <= 0) throw new IllegalArgumentException("amount <= 0");
        if (cmd.getAmount() > remainingValue) throw new IllegalStateException("amount > remaining value");
        AggregateLifecycle.apply(new RedeemedEvt(id, cmd.getAmount()));
    }

    @EventSourcingHandler
    public void on(RedeemedEvt evt) {
        remainingValue -= evt.getAmount();
    }
}
