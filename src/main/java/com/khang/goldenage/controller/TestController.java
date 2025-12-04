package com.khang.goldenage.controller;

import com.khang.goldenage.modal.User;
import com.khang.goldenage.service.CronNotifyUserMailService;
import com.khang.goldenage.service.SendMailService;
import com.khang.goldenage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {
  @Autowired
  private CronNotifyUserMailService cronNotifyUserMailService;
  @Autowired
  private SendMailService sendMailService;
  @Autowired
  private UserService userService;


  @GetMapping("/test-send-email")
  public String testSendEmail() {
    cronNotifyUserMailService.sendDailyGoldPriceNotifications();
    cronNotifyUserMailService.sendDailyExchangeRateNotifications();
    return "Email notifications sent!";
  }

  @PostMapping("/send-email")
  public ResponseEntity<String> sendEmail(@RequestBody Map<String, String> body) {
    String email = body.get("email");
    sendMailService.sendEmail(email, "Cảm ơn bạn đã đăng ký!", "Bạn sẽ nhận tin tức mới nhất từ hệ thống.");
    return ResponseEntity.ok("đã gửi mail cho:" + email);
  }

  @GetMapping("/check")
  public ResponseEntity<Boolean> checkEmailExist(@RequestParam String email) {
    Boolean exists = sendMailService.checkEmail(email);
    return ResponseEntity.ok(exists);
  }
//  @PostMapping("/subcribe")
//  public ResponseEntity<String> subcribe(@RequestBody Map<String, String> body) {
//    String email = body.get("email");
//    if(sendMailService.checkEmail(email)) {
//       return ResponseEntity.badRequest().body("Email đã đc đăng kí");
//    }
//    User user = new User();
//    user.setEmail(email);
//
//
//  }

}
