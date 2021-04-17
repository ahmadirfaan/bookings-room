--Dummy data untuk users
INSERT INTO users(created_at, email, password) VALUES (NOW(), 'irfaan.hibatullah@gmail.com', "irfaan123");
INSERT INTO users(created_at, email, password) VALUES (NOW(), 'ahmadirfaanh@gmail.com', "ahmad123");
INSERT INTO users(created_at, email, password) VALUES (NOW(), 'vargoadventure@gmail.com', "vargo123");
INSERT INTO users(created_at, email, password) VALUES (NOW(), 'solehsolihin@gmail.com', "soleh123");

-- Dummy data ruangan
INSERT INTO rooms(created_at, room_name, room_capacity) VALUES (NOW(), 'Ruangan 1', 10);
INSERT INTO rooms(created_at, room_name, room_capacity) VALUES (NOW(), 'Ruangan 2', 25);
INSERT INTO rooms(created_at, room_name, room_capacity) VALUES (NOW(), 'Ruangan 3', 50);
INSERT INTO rooms(created_at, room_name, room_capacity) VALUES (NOW(), 'Ruangan 4', 100);

-- Dummy data bookings
INSERT INTO bookings(created_at, user_id, room_id, total_person, booking_time, text) VALUES (NOW(), 1, 1, 5, '2021-04-27', "Ruangan Harap Dibersihkan dulu");
INSERT INTO bookings(created_at, user_id, room_id, total_person, booking_time, text) VALUES (NOW(), 2, 2, 15, '2021-04-26', "Ruangan Harap Dibersihkan dulu");
INSERT INTO bookings(created_at, user_id, room_id, total_person, booking_time, text) VALUES (NOW(), 3, 3, 40, '2021-04-26', "Ruangan Harap Dibersihkan dulu");
INSERT INTO bookings(created_at, user_id, room_id, total_person, booking_time, text) VALUES (NOW(), 4, 4, 80, '2021-04-24', "Ruangan Harap Dibersihkan dulu");
