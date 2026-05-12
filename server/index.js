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

const games = {};

function generateQuestion() {

    let maxNumber = 10;

    if (timeLeft <= 45) {
        maxNumber = 20;
    }

    if (timeLeft <= 30) {
        maxNumber = 50;
    }

    if (timeLeft <= 15) {
        maxNumber = 100;
    }

    const num1 =
        Math.floor(Math.random() * maxNumber);

    const num2 =
        Math.floor(Math.random() * maxNumber);

    return {
        question: `${num1} + ${num2}`,
        answer: num1 + num2
    };
}

io.on("connection", (socket) => {

    console.log("Player connected:", socket.id);

    socket.on("findMatch", () => {

        if (waitingPlayer === null) {

            waitingPlayer = socket;

            socket.emit("waiting");

        } else {

            const roomId = `room-${socket.id}-${waitingPlayer.id}`;

            socket.join(roomId);
            waitingPlayer.join(roomId);

            const firstQuestion = generateQuestion(60);

            games[roomId] = {
                scores: {
                    [socket.id]: 0,
                    [waitingPlayer.id]: 0
                },
                currentAnswer: firstQuestion.answer,
                timeLeft: 60
            };

            io.to(roomId).emit("gameStart", {
                roomId,
                question: firstQuestion.question,
                scores: games[roomId].scores,
                timeLeft: 60
            });

            const timer = setInterval(() => {

                games[roomId].timeLeft--;

                io.to(roomId).emit("timerUpdate", {
                    timeLeft: games[roomId].timeLeft
                });

                if (games[roomId].timeLeft <= 0) {

                    clearInterval(timer);

                    io.to(roomId).emit("gameOver", {
                        scores: games[roomId].scores
                    });

                    delete games[roomId];
                }

            }, 1000);

            waitingPlayer = null;
        }
    });

    socket.on("submitAnswer", ({ roomId, answer }) => {

        const game = games[roomId];

        if (!game) return;

        if (parseInt(answer) === game.currentAnswer) {
            socket.emit("correctAnswer");

            game.scores[socket.id]++;

            const newQuestion = generateQuestion();

            game.currentAnswer = newQuestion.answer;

            io.to(roomId).emit("newQuestion", {
                question: newQuestion.question,
                scores: game.scores
            });
        } else {

            socket.emit("wrongAnswer");
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