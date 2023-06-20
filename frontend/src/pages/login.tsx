import { useState } from "react";
import './signup.css';
import serverMiddleware, { loginUser } from "../middleware/server-midlewara";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { changeEmail } from "../models/user_slice";


export default function LoginPage() {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [errorMsg, setErrorMsg] = useState<string>(''); // TODO: Place here error if some data is wrong
    const navigator = useNavigate();
    const dispatch = useDispatch();

    const HandleSubmitBtn = () => {
        console.log("Running...", email, password);
        fetch("http://localhost:8080/api/user/login", { 
            method: 'POST',
            headers: new Headers({
                'content-type': 'application/json'
            }),
            body: JSON.stringify({
                email: email,
                password: password
            })
        }).then((value: Response) => {
            console.debug("Result accepted", value);
            if (value.ok) {
                dispatch(changeEmail(email));
                navigator('/panel');
            }
        }).catch((reason: any) => {
            console.debug("Error appeared", reason)
        });
    };

    const errorHandler = (field: string, reason: string) => {
        setErrorMsg(`Problem with ${field} with reason ${reason}`);
    };

    return <>
        <section>
            <div className="container two-column">
                <div>
                    <h1>Login</h1>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione iste dolorem dolore quibusdam, suscipit, incidunt distinctio iure natus tempora velit doloremque quod accusamus itaque voluptatem voluptatum in iusto. Fuga, explicabo!
                    </p>
                </div>
                <div className="form">
                    <input type="email" placeholder="Email" onChange={(event) => setEmail(event.target.value)} />
                    <input type="password" placeholder="Password" onChange={(event) => setPassword(event.target.value)} />
                    <button onClick={() => HandleSubmitBtn()}>Submit</button>
                    { errorMsg.length == 0 ? 
                        <></> :
                        <div className="error-msg">
                            <h3>Error appeared!</h3>
                            <p>{errorMsg}</p>
                        </div>
                    }
                </div>
            </div>
        </section>
    </>
}
