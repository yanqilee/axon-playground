package com.example.demo.config;

import com.example.demo.aggregate.GiftCard;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnapshotConfig {

//    @Autowired
//    private EventStore eventStore;

    @Bean
    public SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean() {
        return new SpringAggregateSnapshotterFactoryBean();
    }

    @Bean
    public SnapshotTriggerDefinition mySnapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }

//    @Bean(name = "giftCardRepository")
//    public Repository<GiftCard> giftCardRepository(Snapshotter snapshotter) {
//        return EventSourcingRepository.builder(GiftCard.class)
//                .eventStore(eventStore)
//                .snapshotTriggerDefinition(new EventCountSnapshotTriggerDefinition(snapshotter, 5))
//                .build();
//    }
//
//    @Bean(name = "giftCardAggregateFactory")
//    public AggregateFactory<GiftCard> giftCardAggregateFactory() {
//        return new SpringPrototypeAggregateFactory<>("giftCard");
//    }
}
