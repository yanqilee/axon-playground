package com.example.demo.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
public class RedeemCmd {

    @TargetAggregateIdentifier
    private final String id;
    private final Integer amount;
}
