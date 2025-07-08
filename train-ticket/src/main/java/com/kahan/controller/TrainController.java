package com.kahan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kahan.entity.Train;
import com.kahan.repository.TrainRepository;
import com.kahan.service.BookingService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrainController {

    private final TrainRepository trainRepository;
    private final BookingService bookingService;

    // Admin view all trains
    @GetMapping("/admin/trains")
    public String getAllTrains(Model model) {
        List<Train> trains = trainRepository.findAll();
        model.addAttribute("trains", trains);
        return "view-trains";
    }

    // User view all trains
    @GetMapping("/view-trains")
    public String getAllTrainsForUser(Model model) {
        List<Train> trains = trainRepository.findAll();
        model.addAttribute("trains", trains);
        return "view-trains";
    }

    // ✅ Booking UI for a specific train
    @GetMapping("train/book/{trainNo}")
    public String showBookingPage(@PathVariable int trainNo, Model model) {
    Train train = trainRepository.findById(trainNo).orElse(null);
        if (train == null) {
            return "redirect:/view-trains";
        }

        List<String> bookedSeats = bookingService.getBookedSeats(trainNo);

        model.addAttribute("train", train);
        model.addAttribute("bookedSeats", bookedSeats);
        return "booking"; // ➜ Renders booking.html
    }
}
