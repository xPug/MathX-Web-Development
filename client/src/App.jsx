import { useEffect, useState } from "react";
import socket from "./socket";
import {
    auth,
    provider
} from "./firebase";

import {
    signInWithPopup,
    signOut
} from "firebase/auth";
import { db } from "./firebase";
import {
    doc,
    getDoc,
    setDoc,
    updateDoc,
    increment
} from "firebase/firestore";

function App() {

    const [status, setStatus] = useState("Idle");
    const [roomId, setRoomId] = useState("");
    const [question, setQuestion] = useState("");
    const [answer, setAnswer] = useState("");
    const [scores, setScores] = useState({});
    const [timeLeft, setTimeLeft] = useState(60);
    const [feedback, setFeedback] = useState("");
    const [streak, setStreak] = useState(0);
    const [flashColor, setFlashColor] = useState("");
    const [user, setUser] = useState(null);
    const [playerData, setPlayerData] = useState(null);

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

        socket.on("correctAnswer", () => {

            setFeedback("Correct!");
            setFlashColor("#14532d");

            setStreak((prev) => prev + 1);

            setTimeout(() => {
                setFeedback("");
                setFlashColor("");
            }, 500);
        });

        socket.on("wrongAnswer", () => {

            setFeedback("Wrong!");
            setFlashColor("#7f1d1d");

            setStreak(0);

            setTimeout(() => {
                setFeedback("");
            }, 500);
        });

        socket.on("gameOver", async (data) => {

            setScores(data.scores);

            setStatus("Game Over!");

            const yourFinalScore =
                data.scores[socket.id] || 0;

            const opponentEntry =
                Object.entries(data.scores).find(
                    ([id]) => id !== socket.id
                );

            const opponentFinalScore =
                opponentEntry
                    ? opponentEntry[1]
                    : 0;

            const didWin =
                yourFinalScore > opponentFinalScore;

            await updatePlayerStats(didWin);
                });

        return () => {

            socket.off("connect");
            socket.off("waiting");
            socket.off("gameStart");
            socket.off("timerUpdate");
            socket.off("newQuestion");
            socket.off("correctAnswer");
            socket.off("wrongAnswer");
            socket.off("gameOver");
        };

    }, []);

    async function login() {

        try {

            const result =
                await signInWithPopup(
                    auth,
                    provider
                );

            const currentUser =
                result.user;

            setUser(currentUser);

            const userRef =
                doc(db, "players", currentUser.uid);

            const userSnap =
                await getDoc(userRef);

            if (!userSnap.exists()) {

            await setDoc(userRef, {
                name: currentUser.displayName,
                rating: 1000,
                wins: 0,
                losses: 0,
                gamesPlayed: 0,
                highestStreak: 0
            });

            setPlayerData({
                name: currentUser.displayName,
                rating: 1000,
                wins: 0,
                losses: 0,
                gamesPlayed: 0,
                highestStreak: 0
            });

            } else {
                setPlayerData(userSnap.data());
            }

            } catch (error) {

            console.log(error);
        }
    }

    async function logout() {

        await signOut(auth);

        setUser(null);
    }

    async function updatePlayerStats(didWin) {

        if (!user) return;

        const playerRef = doc(db, "players", user.uid);

        await updateDoc(playerRef, {

            gamesPlayed: increment(1),

            wins: didWin
                ? increment(1)
                : increment(0),

            losses: didWin
                ? increment(0)
                : increment(1),

            rating: didWin
                ? increment(25)
                : increment(-15),

            highestStreak: increment(0)
        });
    }

    function findMatch() {

        socket.emit("findMatch");

        setStatus("Searching...");
    }

    function submitAnswer() {

        socket.emit("submitAnswer", {
            roomId,
            answer
        });
        setAnswer("");
    }

    const yourScore =
        scores[socket.id] || 0;

    const opponentEntry =
        Object.entries(scores).find(
            ([id]) => id !== socket.id
        );

    const opponentScore =
        opponentEntry ? opponentEntry[1] : 0;

    return (
        <div style={{
            background: flashColor || "linear-gradient(to bottom, #020617, #0f172a)",
            color: "white",
            minHeight: "100vh",
            display: "flex",
            justifyContent: "flex-start",
            paddingTop: "20px",
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
                    <>

                        {
                            !user
                            ?
                            (
                                <button
                                    onClick={login}

                                    style={{
                                        padding: "15px 40px",
                                        fontSize: "20px",
                                        border: "none",
                                        borderRadius: "10px",
                                        marginBottom: "20px",
                                        cursor: "pointer"
                                    }}
                                >
                                    Sign In With Google
                                </button>
                            )
                            :
                            (
                                <div style={{
                                    textAlign: "center"
                                }}>

                                    <div style={{
                                        width: "90px",
                                        height: "90px",
                                        borderRadius: "50%",
                                        border: "3px solid #7c3aed",
                                        marginBottom: "10px",
                                        margin: "0 auto",
                                        display: "flex",
                                        justifyContent: "center",
                                        alignItems: "center",
                                        fontSize: "38px",
                                        lineHeight: "1",
                                        paddingBottom: "4px",
                                        backgroundColor: "#1e293b"
                                    }}>
                                        👤
                                    </div>

                                    <h2>
                                        {user.displayName}
                                    </h2>

                                    {
                                        playerData &&
                                        (
                                            <h3 style={{
                                                color: "#facc15"
                                            }}>
                                                ⭐ Rating: {playerData.rating}
                                            </h3>
                                        )
                                    }

                                    <button
                                        onClick={logout}

                                        style={{
                                            padding: "8px 20px",
                                            border: "none",
                                            borderRadius: "8px",
                                            backgroundColor: "#ef4444",
                                            color: "white",
                                            cursor: "pointer",
                                            marginBottom: "20px"
                                        }}
                                    >
                                        Logout
                                    </button>

                                </div>
                            )
                        }

                        <button
                            onClick={findMatch}

                            disabled={!user}

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

                    </>
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
                        <div style={{
                            textAlign: "center"
                        }}>

                            <h1 style={{
                                fontSize: "72px",
                                color:
                                timeLeft <= 10
                                ? "#ef4444"
                                : timeLeft <= 20
                                ? "#f59e0b"
                                : "white"
                            }}>
                                {timeLeft}
                            </h1>

                            {
                                timeLeft <= 10 && (
                                    <h2 style={{
                                        color: "#ef4444",
                                        marginTop: "-10px"
                                    }}>
                                        FINAL SECONDS
                                    </h2>
                                )
                            }

                        </div>

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

                        <h2 style={{
                            color:
                                feedback === "Correct!"
                                ? "#22c55e"
                                : "#ef4444",

                            height: "30px"
                        }}>
                            {feedback}
                        </h2>

                        <h2 style={{
                            color: "#f59e0b"
                        }}>
                            🔥 Streak: {streak}
                        </h2>

                        <div style={{
                            display: "flex",
                            justifyContent: "space-between",
                            width: "100%",
                            maxWidth: "500px",
                            marginTop: "20px"
                        }}>

                            <div style={{
                                textAlign: "center"
                            }}>
                                <h2>You</h2>

                                <h1 style={{
                                    fontSize: "50px",
                                    color: "#22c55e"
                                }}>
                                    {yourScore}
                                </h1>
                            </div>

                            <div style={{
                                textAlign: "center"
                            }}>
                                <h2>Opponent</h2>

                                <h1 style={{
                                    fontSize: "50px",
                                    color: "#38bdf8"
                                }}>
                                    {opponentScore}
                                </h1>
                            </div>
                        </div>
                    </>
                )
            }

        </div>
    );
}

export default App;