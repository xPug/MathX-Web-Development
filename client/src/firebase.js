import { initializeApp } from "firebase/app";

import {
    getAuth,
    GoogleAuthProvider
} from "firebase/auth";

const firebaseConfig = {

    apiKey: "AIzaSyDYAV67z2a1CVkV_GLx74Cw-kgwJn7Tqh0",

    authDomain: "mathx-a4830.firebaseapp.com",

    projectId: "mathx-a4830",

    storageBucket: "mathx-a4830.firebasestorage.app",

    messagingSenderId: "782322205273",

    appId: "1:782322205273:web:e064838f8b882409a82748"
};

const app =
    initializeApp(firebaseConfig);

export const auth =
    getAuth(app);

export const provider =
    new GoogleAuthProvider();