package com.project.projectxxx.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.projectxxx.domain.CheckStatus;
import com.project.projectxxx.domain.Reservation;
import com.project.projectxxx.domain.User;
import com.project.projectxxx.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Tag(name = "ReservationController", description = "ReservationController입니다")
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @GetMapping("/reservations/{reservationId}")
    public Result<ReservationDTO> reservationFindOne(@PathVariable Long reservationId){
        Reservation reservationOne = service.findReservationOne(reservationId);
        ReservationDTO reservationDTO = new ReservationDTO(reservationOne);
        return new Result<>(reservationDTO);
    }
    @PostMapping("/reservations")
    public Result<ReservationDTO> reservations(@RequestBody ReservationDayRequest request, HttpSession session){
        User loginUser = (User)session.getAttribute("LOGIN_USER");
        Reservation reservation = new Reservation();
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setStatus(request.getStatus());
        reservation.setCapacity(request.getCapacity());
        reservation.setPrice(request.getPrice());
        reservation.setUser(loginUser);
        Long reservationId = service.reservationSave(reservation);
        ReservationDTO reservationDTO = new ReservationDTO(reservationId, loginUser.getName(),request.getStartDate(),request.getEndDate());
        return new Result<>(reservationDTO);
    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
    @Data
    static class ReservationDayRequest{
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private CheckStatus status;
        private int capacity;
        private int price;
    }
    @Data
    @AllArgsConstructor
    static class ReservationDTO{
        private Long reservationId;
        private String name;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime startDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime endDate;

        public ReservationDTO(Reservation reservation){
            reservationId = reservation.getId();
            name = reservation.getUser().getName();
            startDate = reservation.getStartDate();
            endDate = reservation.getEndDate();
        }
    }
}
