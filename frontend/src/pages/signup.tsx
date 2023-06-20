import { ChangeEvent, ChangeEventHandler, useState } from "react";
import './signup.css';
import { useNavigate } from "react-router-dom";
import { changeEmail } from "../models/user_slice";
import { useDispatch } from "react-redux";


export default function SignupPage() {
    const [email, setEmail] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    // const [errorMsg, setErrorMsg] = useState<string>(''); // TODO: Place here error if some data is wrong

    const navigator = useNavigate();
    const dispatch = useDispatch();

    const HandleSubmitBtn = () => {
        console.log("Running...", email, password);
        fetch('http://localhost:8080/api/user', {
            method: "POST",
            headers: new Headers(
                {'Content-type': 'application/json'}
            ),
            body: JSON.stringify({
                email: email,
                password: password
            })
        }).then((resp: Response) => {
            console.log(resp);
            if(resp.ok) {
                console.log("Okay!");
                dispatch(changeEmail(email));
                console.log(resp.headers.get("Set-Cookie"));
                navigator("/panel");
            }
        }).catch((reason: any) => {
            console.log(reason);
        });
    };

    return <>
        <section>
            <div className="container two-column">
                <div>
                    <h1>Signup</h1>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione iste dolorem dolore quibusdam, suscipit, incidunt distinctio iure natus tempora velit doloremque quod accusamus itaque voluptatem voluptatum in iusto. Fuga, explicabo!
                    </p>
                </div>
                <div className="form">
                    <input type="email" placeholder="Email" onChange={(event) => setEmail(event.target.value)} />
                    <input type="password" placeholder="Password" onChange={(event) => setPassword(event.target.value)} />
                    <button onClick={() => HandleSubmitBtn()}>Submit</button>
                </div>
            </div>
        </section>
    </>
}
