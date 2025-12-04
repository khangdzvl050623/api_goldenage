package com.khang.goldenage.repository;

import com.khang.goldenage.modal.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationsRepository extends JpaRepository<Notification, Long> {
  List<Notification> findByUserOrderByTimestampDesc(User user);
  List<Notification> findByUserAndIsReadFalse(User user); // Lấy thông báo chưa đọc
}

