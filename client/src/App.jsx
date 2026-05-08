import { useEffect, useState } from "react";
import socket from "./socket";

function App() {

    const [message, setMessage] = useState("");

    useEffect(() => {

        socket.on("connect", () => {
            console.log("Connected:", socket.id);
        });

        socket.on("welcome", (data) => {
            setMessage(data);
        });

    }, []);

    return (
        <div style={{
            backgroundColor: "#0f172a",
            color: "white",
            height: "100vh",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            flexDirection: "column",
            fontFamily: "Arial"
        }}>
            <h1>MathX</h1>

            <h2>{message}</h2>
        </div>
    );
}

export default App;