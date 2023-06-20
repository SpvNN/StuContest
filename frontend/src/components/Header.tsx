import { useSelector } from "react-redux";
import { RootState } from "../store";
import './Header.css';
import { User } from "../models/user_slice";

export default function Header() {
    const user: User = useSelector((root: RootState) => root.user);

    return <>
        <header>
            <div className="container">
                <a href="/" className="logo">StuContest</a>
                <nav>
                {user.email == "unknown" ? 
                        <>
                            <a href="/signup">Signup</a>
                            <a href="/login">Log in</a>
                        </> :
                        <>b!</>
                }
                </nav>
            </div>
        </header>
    </>
}
