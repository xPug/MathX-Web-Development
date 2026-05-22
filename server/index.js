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

let waitingPlayer = [];

const games = {};

function generateQuestion(timeLeft) {

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

    socket.on("findMatch", (playerRating = 1000) => {

        const playerData = {
            socket,
            rating: playerRating,
            joinedAt: Date.now()
        };

        const allowedDifference = 200;

        let opponentIndex = -1;

        for (let i = 0; i < waitingPlayers.length; i++) {

            const waiting = waitingPlayers[i];

            const ratingDifference =
                Math.abs(
                    waiting.rating -
                    playerData.rating
                );

            if (ratingDifference <= allowedDifference) {

                opponentIndex = i;

                break;
            }
        }

        if (opponentIndex === -1) {

            waitingPlayers.push(playerData);

            socket.emit("waiting");

            return;
        }

        const opponent =
            waitingPlayers[opponentIndex];

        waitingPlayers.splice(opponentIndex, 1);

        const roomId =
            `room-${socket.id}-${opponent.socket.id}`;

        socket.join(roomId);

        opponent.socket.join(roomId);

        const firstQuestion =
            generateQuestion(60);

        games[roomId] = {

            scores: {
                [socket.id]: 0,
                [opponent.socket.id]: 0
            },

            currentAnswer:
                firstQuestion.answer,

            timeLeft: 60
        };

        io.to(roomId).emit("gameStart", {

            roomId,

            question:
                firstQuestion.question,

            scores:
                games[roomId].scores,

            timeLeft: 60
        });

        const timer = setInterval(() => {

            games[roomId].timeLeft--;

            io.to(roomId).emit("timerUpdate", {
                timeLeft:
                    games[roomId].timeLeft
            });

            if (games[roomId].timeLeft <= 0) {

                clearInterval(timer);

                io.to(roomId).emit("gameOver", {
                    scores:
                        games[roomId].scores
                });

                delete games[roomId];
            }

        }, 1000);
    });

    socket.on("submitAnswer", ({ roomId, answer }) => {

        const game = games[roomId];

        if (!game) return;

        if (parseInt(answer) === game.currentAnswer) {
            socket.emit("correctAnswer");

            game.scores[socket.id]++;

            const newQuestion = generateQuestion(game.timeLeft);

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