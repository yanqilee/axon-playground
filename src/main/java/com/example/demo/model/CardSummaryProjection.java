package com.example.demo.model;

import com.example.demo.event.IssuedEvt;
import com.example.demo.event.RedeemedEvt;
import com.example.demo.query.FetchCardSummariesQuery;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CardSummaryProjection {

    private final CardSummaryRepository cardSummaryRepository;

    @EventHandler // (1)
    public void on(IssuedEvt evt) {
        CardSummary cardSummary = new CardSummary(evt.getId(), evt.getAmount(), evt.getAmount());
        cardSummaryRepository.save(cardSummary);
    }

    @EventHandler
    public void on(RedeemedEvt evt) {
        cardSummaryRepository.findAllById(evt.getId())
                .stream()
                .findFirst()
                .ifPresent(cardSummary -> {
                    CardSummary updatedCardSummary = cardSummary.deductAmount(evt.getAmount());
                    cardSummaryRepository.delete(cardSummary);
                    cardSummaryRepository.save(updatedCardSummary);
                });
    }

    @QueryHandler // (2)
    public List<CardSummary> fetch(FetchCardSummariesQuery query) {
        return cardSummaryRepository.findAll();
    }
}
