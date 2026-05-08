const express = require("express");
const http = require("http");
const { Server } = require("socket.io");
const cors = require("cors");

const app = express();

app.use(cors());

const server = http.createServer(app);

const io = new Server(server, {
    cors: {
        origin: "http://localhost:5173",
        methods: ["GET", "POST"]
    }
});

let waitingPlayer = null;

io.on("connection", (socket) => {

    console.log("Player connected:", socket.id);

    socket.on("findMatch", () => {

        console.log(socket.id, "is looking for a match");

        if (waitingPlayer === null) {

            waitingPlayer = socket;

            socket.emit("waiting");

        } else {

            const roomId = `room-${socket.id}-${waitingPlayer.id}`;

            socket.join(roomId);
            waitingPlayer.join(roomId);

            io.to(roomId).emit("matchFound", {
                roomId
            });

            waitingPlayer = null;
        }
    });

    socket.on("disconnect", () => {

        console.log("Player disconnected:", socket.id);

        if (waitingPlayer === socket) {
            waitingPlayer = null;
        }
    });
});

server.listen(3001, () => {
    console.log("Server running on port 3001");
});