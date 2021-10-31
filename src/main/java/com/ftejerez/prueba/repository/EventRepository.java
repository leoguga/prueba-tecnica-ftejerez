package com.ftejerez.prueba.repository;

import com.ftejerez.prueba.model.EventType;
import com.ftejerez.prueba.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndEventTypeLike(Date dateFrom, Date dateTo, EventType eventType);

}
