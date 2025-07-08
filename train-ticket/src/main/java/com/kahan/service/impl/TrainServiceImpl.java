package com.kahan.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.kahan.entity.Train;
import com.kahan.repository.TrainRepository;
import com.kahan.service.TrainService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainByNumber(int trainNo) {
        return trainRepository.findById(trainNo).orElse(null);
    }

    @Override
    public void addTrain(Train train) {
        train.setTotalSeats(train.getNumberOfCoaches() * 60);
        train.setAvailableSeats(train.getTotalSeats());
        trainRepository.save(train);
    }

    @Override
    public void updateTrain(Train train) {
        Optional<Train> existingOpt = trainRepository.findById(train.getTrainNo());
        if (existingOpt.isPresent()) {
            Train existing = existingOpt.get();
            existing.setTrainName(train.getTrainName());
            existing.setSource(train.getSource());
            existing.setDestination(train.getDestination());
            existing.setFare(train.getFare());

            int oldTotal = existing.getTotalSeats();
            int oldAvailable = existing.getAvailableSeats();
            int newTotal = train.getTotalSeats();

            int newAvailable = oldAvailable + (newTotal - oldTotal);
            existing.setTotalSeats(newTotal);
            existing.setAvailableSeats(Math.max(newAvailable, 0));

            trainRepository.save(existing);
        }
    }

    @Override
    public void deleteTrain(int trainNo) {
        trainRepository.deleteById(trainNo);
    }
}