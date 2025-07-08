package com.kahan.service;

import java.util.List;

import com.kahan.entity.Train;

public interface TrainService {
    List<Train> getAllTrains();
    Train getTrainByNumber(int trainNo);
    void addTrain(Train train);
    void updateTrain(Train train);
    void deleteTrain(int trainNo);
}
