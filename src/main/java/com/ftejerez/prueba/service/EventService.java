package com.ftejerez.prueba.service;

import com.ftejerez.prueba.model.Event;
import com.ftejerez.prueba.model.EventType;
import com.ftejerez.prueba.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> searchFlightsByEventTypeWithinDateRange(Date dateFrom, Date dateTo, EventType eventType) {
        return eventRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndEventTypeLike(dateFrom, dateTo, eventType);
    }

}
