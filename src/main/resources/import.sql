--Dummy data untuk users
INSERT INTO users(created_at, email, password, photo) VALUES (NOW(), 'irfaan.hibatullah@gmail.com', "irfaan123" , "https://storage.googleapis.com/booking-room-d926a.appspot.com/2ef150c3-5449-4860-a7ba-4904b18bd788.jpg");
INSERT INTO users(created_at, email, password, photo) VALUES (NOW(), 'ahmadirfaanh@gmail.com', "ahmad123", "https://storage.googleapis.com/booking-room-d926a.appspot.com/2ef150c3-5449-4860-a7ba-4904b18bd788.jpg");
INSERT INTO users(created_at, email, password, photo) VALUES (NOW(), 'vargoadventure@gmail.com', "vargo123", "https://storage.googleapis.com/booking-room-d926a.appspot.com/2ef150c3-5449-4860-a7ba-4904b18bd788.jpg");
INSERT INTO users(created_at, email, password, photo) VALUES (NOW(), 'solehsolihin@gmail.com', "soleh123", "https://storage.googleapis.com/booking-room-d926a.appspot.com/2ef150c3-5449-4860-a7ba-4904b18bd788.jpg");

-- Dummy data ruangan
INSERT INTO rooms(created_at, room_name, room_capacity, photo) VALUES (NOW(), 'Ruangan 1', 10, "https://storage.googleapis.com/booking-room-d926a.appspot.com/2ef150c3-5449-4860-a7ba-4904b18bd788.jpg");
INSERT INTO rooms(created_at, room_name, room_capacity, photo) VALUES (NOW(), 'Ruangan 2', 25, "https://storage.googleapis.com/booking-room-d926a.appspot.com/2ef150c3-5449-4860-a7ba-4904b18bd788.jpg");
INSERT INTO rooms(created_at, room_name, room_capacity, photo) VALUES (NOW(), 'Ruangan 3', 50, "https://storage.googleapis.com/booking-room-d926a.appspot.com/2ef150c3-5449-4860-a7ba-4904b18bd788.jpg");
INSERT INTO rooms(created_at, room_name, room_capacity, photo) VALUES (NOW(), 'Ruangan 4', 100, "https://storage.googleapis.com/booking-room-d926a.appspot.com/2ef150c3-5449-4860-a7ba-4904b18bd788.jpg");


-- Dummy data bookings
INSERT INTO bookings(created_at, user_id, room_id, total_person, booking_time, text) VALUES (NOW(), 1, 1, 5, '2021-04-27', "Ruangan Harap Dibersihkan dulu");
INSERT INTO bookings(created_at, user_id, room_id, total_person, booking_time, text) VALUES (NOW(), 2, 2, 15, '2021-04-26', "Ruangan Harap Dibersihkan dulu");
INSERT INTO bookings(created_at, user_id, room_id, total_person, booking_time, text) VALUES (NOW(), 3, 3, 40, '2021-04-26', "Ruangan Harap Dibersihkan dulu");
INSERT INTO bookings(created_at, user_id, room_id, total_person, booking_time, text) VALUES (NOW(), 4, 4, 80, '2021-04-24', "Ruangan Harap Dibersihkan dulu");
