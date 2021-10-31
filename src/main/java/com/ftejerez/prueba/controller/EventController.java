package com.ftejerez.prueba.controller;

import com.ftejerez.prueba.service.EventService;
import com.ftejerez.prueba.dto.ErrorResponse;
import com.ftejerez.prueba.exception.InvalidDateRangeException;
import com.ftejerez.prueba.model.Event;
import com.ftejerez.prueba.model.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private static final Logger log = LoggerFactory.getLogger(EventController.class);

    public static final long FULL_DAY_MINUS_1_MILLISECOND = 1000 * 3600 * 24 - 1;

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/searchFlights")
    List<Event> searchFlights(@RequestParam("dateFrom") @DateTimeFormat(pattern="yyyy-MM-dd") final Date dateFrom,
                              @RequestParam("dateTo") @DateTimeFormat(pattern="yyyy-MM-dd") final Date dateTo,
                              @RequestParam("eventType") final EventType eventType) {
        if (dateFrom.after(dateTo)) {
            throw new InvalidDateRangeException("dateFrom must be before dateTo");
        }

        Date dateToEndOfTheDay = new Date(dateTo.getTime() + FULL_DAY_MINUS_1_MILLISECOND);

        log.info("Search Flights query values:");
        log.info("-------------------------------");
        log.info(dateFrom.toString());
        log.info(dateToEndOfTheDay.toString());
        log.info(eventType.toString());
        log.info("");

        return eventService.searchFlightsByEventTypeWithinDateRange(dateFrom, dateToEndOfTheDay, eventType);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class,
            InvalidDateRangeException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage()));
    }

}
