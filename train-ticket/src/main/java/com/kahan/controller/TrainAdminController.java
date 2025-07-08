package com.kahan.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kahan.entity.Train;
import com.kahan.service.TrainService;

@Controller
@RequestMapping("/admin/train")
@RequiredArgsConstructor
public class TrainAdminController {

    private final TrainService trainService;

    // Show add train form
    @GetMapping("/add")
    public String showAddTrainForm(Model model) {
        model.addAttribute("train", new Train());
        return "add-train";
    }

    // Handle add train form submission
    @PostMapping("/add")
    public String addTrain(@ModelAttribute Train train) {
        int totalSeats = train.getNumberOfCoaches() * 60;
        train.setAvailableSeats(totalSeats);
        trainService.addTrain(train);
        return "redirect:/admin/trains";
    }

    // Show edit train form
    @GetMapping("/edit/{trainNo}")
    public String showEditForm(@PathVariable int trainNo, Model model) {
        Train train = trainService.getTrainByNumber(trainNo);
        model.addAttribute("train", train);
        return "edit-train";
    }

    // Handle edit train form submission
    @PostMapping("/edit")
    public String editTrain(@ModelAttribute Train train) {
        int totalSeats = train.getNumberOfCoaches() * 60;
        train.setTotalSeats(totalSeats);         // ✅ fix: also set totalSeats
        train.setAvailableSeats(totalSeats);     
        trainService.updateTrain(train);
        return "redirect:/admin/trains";
    }

    // Handle delete train
    @GetMapping("/delete/{trainNo}")
    public String deleteTrain(@PathVariable int trainNo) {
        trainService.deleteTrain(trainNo);
        return "redirect:/admin/trains";
    }

    // List trains for admin (optional, if you want admin to see the list)
    @GetMapping("/list")
    public String listTrains(Model model) {
        List<Train> trains = trainService.getAllTrains();
        model.addAttribute("trains", trains);
        return "view-trains";
    }
}