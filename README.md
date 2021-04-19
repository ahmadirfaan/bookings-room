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

# Dokumentasi GraphQL

​	Pembuatan GraphQL mengikuti schema di bawah ini atau dalam folder `/main/resource/graphql/schema.graphqls`

```graphql
schema {
    query: Query
    mutation : Mutation
}

type Query{
    getAllUsers: [Users]
    getAllRooms : [Rooms]
    getAllBookings : [Bookings]
}

type Bookings {
    createdAt : String,
    updatedAt : String,
    deletedAt : String,
    id : Int,
    userId : Users,
    roomId : Rooms,
    totalPerson : Int,
    bookingTime : String,
    noted: String,
    checkInTime: String,
    checkOutTime : String
}

type Users {
    createdAt : String,
    updatedAt : String,
    deletedAt : String,
    id : Int!,
    email : String,
    password : String,
    photo : String
}

type Rooms {
    createdAt : String,
    updatedAt : String,
    deletedAt : String,
    id : Int,
    roomName : String,
    roomCapacity: Int,
    photo: String
}

type Mutation{
    createUser(email: String!, password: String!) : Users
    createRoom(roomName : String!, roomCapacity: Int!) : Rooms
    createBooking(userId: Int!, roomId:Int, totalPerson:Int!, bookingTime:String!, noted: String!) : Bookings
    updateUser(id: Int, email: String!, password: String!) : Users
    updateRoom(id: Int, roomName : String!, roomCapacity: Int!) : Rooms
    updateBooking(id : Int, userId: Int!, roomId:Int, totalPerson:Int!, bookingTime:String!, noted: String!) : Bookings
    getUserById(id:Int!): Users
    getRoomById(id:Int!) : Rooms
    getBookingById(id:Int!) : Bookings
    softDeleteUserById(id:Int!): Users
    softDeleteRoomById(id:Int!) : Rooms
    softDeleteBookingById(id:Int!) : Bookings
}
```

