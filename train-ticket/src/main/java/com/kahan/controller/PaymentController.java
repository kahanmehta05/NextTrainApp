package com.kahan.controller;

import com.kahan.entity.Booking;
import com.kahan.entity.User;
import com.kahan.repository.BookingRepository;
import com.kahan.service.BookingService;
import com.kahan.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;



@Controller
public class PaymentController {

    private final BookingRepository bookingRepository;
    private final BookingService bookingService;
    private final UserService userService;
    private final RazorpayClient razorpay;

    @Autowired
    public PaymentController(BookingRepository bookingRepository,
                             BookingService bookingService,
                             UserService userService) throws Exception {
        this.bookingRepository = bookingRepository;
        this.bookingService = bookingService;
        this.userService = userService;
        // Initialize Razorpay client here
        this.razorpay = new RazorpayClient("rzp_test_AXa5IGEOp6MUpe", "nhU0vvAMkLKST8bROMlXlZT6");
    }

    // Create Razorpay order and return JSON
    @PostMapping("/createOrder")
    @ResponseBody
    public Map<String, Object> createOrder(@RequestParam double amount) throws Exception {
        JSONObject options = new JSONObject();
        options.put("amount", (int)(amount * 100)); // amount in paise
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());
        Order order = razorpay.orders.create(options);

        Map<String, Object> response = new HashMap<>();
        response.put("id", order.get("id"));
        response.put("amount", order.get("amount"));
        response.put("currency", order.get("currency"));
        return response;
    }

// @GetMapping("/download-ticket/{bookingId}")
// public void downloadTicket(@PathVariable String bookingId, HttpServletResponse response) throws IOException {
//     // PDF generation logic here
//     String html = "..." ; // (use the HTML string from the previous answer)
//     response.setContentType("application/pdf");
//     response.setHeader("Content-Disposition", "attachment; filename=ticket.pdf");
//     try (OutputStream os = response.getOutputStream()) {
//         PdfRendererBuilder builder = new PdfRendererBuilder();
//         builder.withHtmlContent(html, null);
//         builder.toStream(os);
//         builder.run();
//     }
// }

    // Verify payment and update booking
    @PostMapping("/verify")
    public String verifyPayment(@RequestParam String bookingId,
                                @RequestParam String razorpay_payment_id,
                                @RequestParam String razorpay_order_id,
                                @RequestParam String razorpay_signature,
                                @AuthenticationPrincipal UserDetails userDetails,
                                Model model) {


        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);

        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setPaid(true);

            User user = userService.getUserByUsername(userDetails.getUsername());
            booking.setUser(user);

            bookingService.updateBooking(booking);
            model.addAttribute("booking", booking);
            return "redirect:/ticket-success?bookingId=" + bookingId;
        }

        return "redirect:/view-bookings";
    }

}