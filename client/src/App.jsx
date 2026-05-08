import { useEffect, useState } from "react";
import socket from "./socket";

function App() {

    const [status, setStatus] = useState("Idle");

    useEffect(() => {

        socket.on("connect", () => {
            console.log("Connected:", socket.id);
        });

        socket.on("waiting", () => {
            setStatus("Waiting for opponent...");
        });

        socket.on("matchFound", (data) => {
            setStatus(`Match Found! Room: ${data.roomId}`);
        });

    }, []);

    function findMatch() {

        socket.emit("findMatch");

        setStatus("Searching...");
    }


    return (
        <div style={{
            backgroundColor: "#0f172a",
            color: "white",
            height: "100vh",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexDirection: "column",
            fontFamily: "Arial",
            gap: "20px"
        }}>

            <h1 style={{
                fontSize: "60px"
            }}>
                MathX
            </h1>

            <button
                onClick={findMatch}
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
                PLAY
            </button>

            <h2>{status}</h2>
        </div>
    );
}

export default App;