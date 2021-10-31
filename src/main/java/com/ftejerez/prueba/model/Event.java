package com.ftejerez.prueba.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = Event.TABLE_NAME)
public class Event {

    public static final String TABLE_NAME = "EVENTS";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idEvent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss", timezone = "Europe/Madrid")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH.mm.ss", timezone = "Europe/Madrid")
    private Date endDate;

    private String resource;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String captain;

    public Event() {
    }

    public Event(Long idEvent,
                 Date startDate,
                 Date endDate,
                 String resource,
                 EventType eventType,
                 String captain) {
        this.idEvent = idEvent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.resource = resource;
        this.eventType = eventType;
        this.captain = captain;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getResource() {
        return resource;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getCaptain() {
        return captain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(idEvent, event.idEvent) &&
                Objects.equals(startDate, event.startDate) &&
                Objects.equals(endDate, event.endDate) &&
                Objects.equals(resource, event.resource) &&
                eventType == event.eventType &&
                Objects.equals(captain, event.captain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvent, startDate, endDate, resource, eventType, captain);
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", resource='" + resource + '\'' +
                ", eventType='" + eventType + '\'' +
                ", captain='" + captain + '\'' +
                '}';
    }
}
