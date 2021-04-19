# DESKRIPSI PROJECT

1.  Project merupakan sebuah aplikasi tentang pemesanan sebuah ruangan. Dengan menggunakan teknologi Spring Boot Java membangun REST API
2.  Penyimpanan gambar menggunakan Firebase
3.  Pemesan akan mendapatkan email terkait detail pesanan ruangan



# Dokumentasi REST API



- ### 	Users

  - Mendapatkan List *Users* : `GET dengan endpoint : /users `

  - Mendapatkan *Users* berdasarkan Id `GET dengan endpoint : /users/{id}`

  - Menambahkan *User* baru : `POST dengan endpoint : /users`

    dengan request body JSON :

    ```json
    {
        "email" : "irfaan.hibatullah@gmail.com",
        "password" : "hibatullah22"
    }
    ```

  - Mengedit *user* berdasarkan id dengan request body Json di atas : `PUT /users/{id}`

  - *Soft delete* *user* berdasarkan id : `PUT /users/{id}`

  - *Upload photo* *user* dengan *multipart file*  `POST dengan endpoint : /users/upload/{id}` , Sehingga data foto akan tersimpan dalam cloud Firebase dan URL akan tersimpan di database.

- ​	**Rooms**

  - Mendapatkan data semua *Rooms* : `GET dengan endpoint : /rooms`

  - Mendapatkan *Rooms* berdasarkan Id `GET dengan endpoint : /rooms/{id}`

  - Menambahkan *Room* baru : `POST dengan endpoint : /rooms`

    dengan request body JSON :

    ```json
    {
        "roomName" : "Ruangan 1",
        "roomCapacity" : "3"
    }
    ```

  - Mengedit *user* berdasarkan id dengan request body Json di atas : `PUT /rooms/{id}`

  - *Soft delete* *customer* berdasarkan id : `PUT /rooms/{id}`

- ​	**Bookings**

  - Mendapatkan data semua *Bookings* : `GET dengan endpoint : /bookings`

  - Mendapatkan *Bookings* berdasarkan Id `GET dengan endpoint : /bookings/{id}`

  - Menambahkan data *booking* baru : `POST dengan endpoint : /bookings`

    dengan request body JSON :

    ```json
    {
        "userId" : 1,
        "roomId" : 3,
        "totalPerson" : 10,
        "bookingtime" : "2021-04-26", //Data string yang di parse : yyyy-MM-dd
        "noted" : "Ini catatan"
    }
    ```

  - Mengedit *user* berdasarkan id dengan request body Json di atas : `PUT /bookings`/{id}`

  - *Soft delete* *customer* berdasarkan id : `PUT /bookings/{id}`

  - Check In data rooms : `PUT /bookings/{id}/check-in`

  - Check Out data rooms : `PUT /bookings/{id}/check-out`

