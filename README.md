🧾 Billing Software Application

A **full-stack Billing & Inventory Management System** built using **Spring Boot** for the backend and **React.js** for the frontend.
The application supports **role-based access**, **order management**, **category & item management**, **UPI/Cash payments**, and a **dashboard with analytics**.

## 🚀 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security (JWT Authentication)
* Spring Data JPA
* Hibernate
* MySQL
* REST APIs

### Frontend

* React.js
* React Router
* Context API
* Axios
* Bootstrap 5
* Custom CSS
* React Hot Toast

## ✨ Key Features

### 🔐 Authentication & Authorization

* JWT-based login system
* Role-based access:

  * ADMIN
  * USER
* Protected routes on frontend & backend

### 📊 Dashboard

* Today’s total sales
* Today’s order count
* Recent orders list

### 🗂 Category Management (Admin)

* Create categories with:

  * Name
  * Description
  * Image
  * Background color
* View all categories
* Search categories
* Delete categories
* Dynamic UI colors based on backend data

### 📦 Item Management (Admin)

* Add items under categories
* Upload item images
* Price & stock handling
* Manage inventory easily

### 🧾 Order Management

* Place orders with multiple items
* Automatic total, tax & grand total calculation
* View complete order history
* Orders sorted by latest first

### 💳 Payment Handling

* UPI Payment (Dummy Razorpay flow)
* Cash Payment
* Payment status tracking:

  * PENDING
  * COMPLETED

### 🧑 User Management (Admin)

* View users
* Role-based restrictions
* Secure admin-only routes

## 🖥️ Screenshots


## 📁 Project Structure

```
BillingSoftware/
│
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── security/
│   └── BillingSoftwareApplication.java
│
├── frontend/
│   ├── components/
│   ├── pages/
│   ├── context/
│   ├── services/
│   ├── assets/
│   └── App.jsx
│
└── README.md
```

## ⚙️ Backend Setup (Spring Boot)

### 1️⃣ Clone the repository

```bash
git clone https://github.com/your-username/billing-software.git
```

### 2️⃣ Configure MySQL

Create a database:

```sql
CREATE DATABASE billing_app;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/billing_app
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Run the backend

```bash
mvn spring-boot:run
```

Backend will run at:

```
http://localhost:8080
```

---

## 🌐 Frontend Setup (React)

### 1️⃣ Navigate to frontend folder

```bash
cd frontend
```

### 2️⃣ Install dependencies

```bash
npm install
```

### 3️⃣ Start the frontend

```bash
npm run dev
```

Frontend will run at:

```
http://localhost:5173
```

---

## 🔑 Default Roles

| Role  | Access                                         |
| ----- | ---------------------------------------------- |
| ADMIN | Full access (categories, items, users, orders) |
| USER  | Explore items, place orders, view orders       |

---

## 🛡 Security Highlights

* JWT stored securely in localStorage
* Axios interceptor for Authorization header
* Backend route protection using Spring Security
* Frontend route guards using React Router

---

## 📌 API Highlights

| Method | Endpoint         | Description     |
| ------ | ---------------- | --------------- |
| POST   | `/auth/login`    | Login           |
| GET    | `/categories`    | Get categories  |
| POST   | `/orders`        | Create order    |
| GET    | `/orders/latest` | Latest orders   |
| GET    | `/dashboard`     | Dashboard stats |

---

## 🧠 Learning Outcomes

This project demonstrates:

* Full-stack application architecture
* Clean separation of concerns
* Secure authentication & authorization
* Real-world CRUD operations
* UI/UX with dynamic backend data
* Error handling & validations

---

## 🚧 Future Enhancements

* Pagination for orders
* Real Razorpay integration
* PDF invoice generation
* Reports export (Excel/PDF)
* Dark/Light theme toggle
* Product stock alerts

---

## 👨‍💻 Author

**Abhishek Patro**
💼 Full-Stack Java Developer
📍 India
