package com.example.demo.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RedeemedEvt {

    private final String id;
    private final Integer amount;
}
