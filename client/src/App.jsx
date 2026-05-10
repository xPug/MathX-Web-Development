import { useEffect, useState } from "react";
import socket from "./socket";

function App() {

    const [status, setStatus] = useState("Idle");
    const [roomId, setRoomId] = useState("");
    const [question, setQuestion] = useState("");
    const [answer, setAnswer] = useState("");
    const [scores, setScores] = useState({});
    const [timeLeft, setTimeLeft] = useState(60);

    useEffect(() => {

        socket.on("connect", () => {
            console.log("Connected:", socket.id);
        });

        socket.on("waiting", () => {
            setStatus("Waiting for opponent...");
        });

        socket.on("gameStart", (data) => {

            setStatus("Game Started!");

            setRoomId(data.roomId);

            setQuestion(data.question);

            setScores(data.scores);

            setTimeLeft(data.timeLeft);
        });

        socket.on("timerUpdate", (data) => {
            setTimeLeft(data.timeLeft);
        });

        socket.on("newQuestion", (data) => {

            setQuestion(data.question);

            setScores(data.scores);

            setAnswer("");
        });

        socket.on("gameOver", (data) => {

            setScores(data.scores);

            setStatus("Game Over!");
        });

    }, []);

    function findMatch() {

        socket.emit("findMatch");

        setStatus("Searching...");
    }

    function submitAnswer() {

        socket.emit("submitAnswer", {
            roomId,
            answer
        });
    }

    return (
        <div style={{
            background: "linear-gradient(to bottom, #020617, #0f172a)",
            color: "white",
            height: "100vh",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexDirection: "column",
            fontFamily: "Arial",
            gap: "10px"
        }}>

            <h1 style={{
                fontSize: status === "Game Started!" ? "48px" : "72px",
                marginBottom: "20px",
                fontWeight: "bold"
            }}>
                MathX
            </h1>

            {
                status === "Idle" ||
                status === "Searching..." ||
                status === "Waiting for opponent..."
                ?
                (
                    <button
                        onClick={findMatch}
                        style={{
                            padding: "18px 50px",
                fontSize: "24px",
                border: "none",
                borderRadius: "14px",
                background: "linear-gradient(to right, #7c3aed, #9333ea)",
                color: "white",
                cursor: "pointer",
                fontWeight: "bold",
                boxShadow: "0 0 20px rgba(124, 58, 237, 0.5)"
                        }}
                    >
                        PLAY
                    </button>
                )
                :
                status === "Game Over!"
                ?
                (
                    <>
                        <h1 style={{
                            fontSize: "64px",
                            marginBottom: "10px"
                        }}>
                            Game Over!
                        </h1>

                        <div>
                            {
                                Object.entries(scores).map(([id, score]) => (
                                    <h2 key={id}>
                                        {id === socket.id ? "You" : "Opponent"}: {score}
                                    </h2>
                                ))
                            }
                        </div>

                        <button
                            onClick={() => window.location.reload()}
                            style={{
                                padding: "15px 40px",
                                fontSize: "20px",
                                border: "none",
                                borderRadius: "10px",
                                backgroundColor: "#7c3aed",
                                color: "white",
                                cursor: "pointer"
                            }}
                        >
                            Play Again
                        </button>
                    </>
                )
                :
                (
                    <>
                        <h1 style={{
                            fontSize: "72px",
                            color: timeLeft <= 10 ? "#ef4444" : "white"
                        }}>
                            {timeLeft}
                        </h1>

                        <h1 style={{
                            fontSize: "72px",
                            marginBottom: "40px"
                        }}>
                            {question}
                        </h1>

                        <input
                            value={answer}
                            onChange={(e) => setAnswer(e.target.value)}

                            onKeyDown={(e) => {
                                if (e.key === "Enter") {
                                    submitAnswer();
                                }
                        }}

                        placeholder="Answer"

                        style={{
                            padding: "10px",
                            fontSize: "20px"
                        }}
                    />

                        <button
                            onClick={submitAnswer}
                            style={{
                                padding: "10px 30px",
                                fontSize: "18px",
                                border: "none",
                                borderRadius: "10px",
                                backgroundColor: "#22c55e",
                                color: "white",
                                cursor: "pointer"
                            }}
                        >
                            Submit
                        </button>

                        <div style={{
                            display: "flex",
                            gap: "100px",
                            marginTop: "20px"
                        }}>
                            {
                                Object.entries(scores).map(([id, score]) => (

                                    <div
                                        key={id}

                                        style={{
                                            textAlign: "center"
                                        }}
                                    >
                                        <h2>
                                            {id === socket.id ? "You" : "Opponent"}
                                        </h2>

                                        <h1 style={{
                                            fontSize: "50px"
                                        }}>
                                            {score}
                                        </h1>
                                    </div>
                                ))
                            }
                        </div>
                    </>
                )
            }

        </div>
    );
}

export default App;