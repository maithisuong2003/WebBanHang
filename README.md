# Web Bán Hàng

Dự án Website Bán Hàng Fullstack với:
- **Frontend:** ReactJS
- **Backend:** Spring Boot

## 🚀 Giới thiệu

Đây là dự án website bán hàng online cho phép người dùng xem sản phẩm, đặt hàng, đăng nhập/đăng ký tài khoản.

## 🛠️ Công nghệ sử dụng

- **Frontend:**  
  - ReactJS
  - Axios (gọi API)
  - React Router
  - Bootstrap

- **Backend:**  
  - Spring Boot
  - Spring Data JPA
  - Spring Security 
  - MySQL
  - JWT 
  - RESTful API

## 📁 Cấu trúc thư mục
WebBanHang/
│
├── backend/          
│   ├── src/
│   └── ...
│
├── frontend/ 
│   ├── src/
│   └── ...
│
└── README.md


## ⚙️ Hướng dẫn cài đặt & chạy project

### 1. Clone source code

git clone https://github.com/maithisuong2003/WebBanHang.git
cd WebBanHang

### 2. Cài đặt & chạy Backend (Spring Boot)

cd backend
# Cấu hình file application.properties (database, JWT secret, ...)
./mvnw spring-boot:run
# hoặc
mvn spring-boot:run

### 3. Cài đặt & chạy Frontend (ReactJS)


cd ../frontend
npm install
npm start

### 4. Truy cập website

- Frontend: http://localhost:3000  
- Backend API: http://localhost:8080

## 💡 Chức năng chính

- Đăng ký, đăng nhập, phân quyền người dùng
- Quản lý sản phẩm, đơn hàng
- Tìm kiếm, lọc, xem chi tiết sản phẩm
- Giỏ hàng, đặt hàng
- Quản lý người dùng, sản phẩm 

## 📚 Tài liệu/tham khảo

- [ReactJS](https://reactjs.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MySQL](https://www.mysql.com/)

## 📝 Thông tin liên hệ

- Tác giả: https://github.com/maithisuong2003
- Email: suongmai.pc2003@gmail.com


