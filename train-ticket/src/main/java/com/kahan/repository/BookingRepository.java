package com.kahan.repository;

import com.kahan.entity.Booking;
import com.kahan.entity.Train;
import com.kahan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findByUser(User user);

    List<Booking> findByTrain(Train train);

    List<Booking> findByUser_Username(String username);

    List<Booking> findByTrain_TrainNo(int trainNo); // For filtering by train number
}
