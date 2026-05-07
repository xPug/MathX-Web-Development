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

io.on("connection", (socket) => {

    console.log("Player connected:", socket.id);

    socket.emit("welcome", "Welcome to MathX!");

    socket.on("disconnect", () => {
        console.log("Player disconnected:", socket.id);
    });

});

server.listen(3001, () => {
    console.log("Server running on port 3001");
});