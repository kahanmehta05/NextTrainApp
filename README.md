# 🚆 NextTrainApp – Online Train Ticket Booking System

NextTrainApp is a full-featured Spring Boot-based web application for train ticket reservations. It supports user registration, real-time seat availability, booking, Razorpay payment, PDF ticket generation, email notifications, and complete admin management.

---

## 🌟 Features

### 👤 User
- Register and login securely
- View all available trains
- Real-time seat availability based on coach configuration
- Book up to 4 tickets per user per train
- Pay securely via Razorpay gateway
- Download ticket as PDF after payment
- 📧 **Email confirmation sent automatically after booking**

### 🛠️ Admin<img width="1357" alt="Screenshot 2025-07-08 at 1 14 47 PM" src="https://github.com/user-attachments/assets/585de3bf-6ee2-4121-84e8-0fc1daf0656e" />

- Admin-only login dashboard
- Add/edit/delete train records
- Set number of coaches per train (60 seats each)
- View all bookings and user information

---

## 🔐 Roles

- **USER** – Default role after registration
- **ADMIN** – Hardcoded credentials:
  - **Username**: `admin`
  - **Password**: `admin123`

---

## 💻 Tech Stack

| Layer         | Technology                     |
|---------------|-------------------------------|
| Backend       | Java, Spring Boot             |
| Frontend      | Thymeleaf, Tailwind CSS       |
| Database      | H2 (in-memory)                |
| Security      | Spring Security, BCrypt       |
| Payments      | Razorpay API                  |
| Email         | Spring Boot Mail (Gmail SMTP) |
| PDF Export    | OpenHTMLtoPDF                 |
| Build Tool    | Gradle                        |

---

## 🚀 Setup Instructions

### 🔧 Prerequisites
- Java 17+
- Gradle
- Internet connection (for email & Razorpay)

### ▶️ Run Locally

```bash
git clone https://github.com/Kahanmehta05/NextTrainApp.git
cd NextTrainApp
./gradlew bootRun
