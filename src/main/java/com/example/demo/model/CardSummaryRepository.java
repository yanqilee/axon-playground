package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardSummaryRepository extends JpaRepository<CardSummary, Long> {

    List<CardSummary> findAllById(String id);
}
