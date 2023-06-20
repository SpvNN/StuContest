import { useState } from "react";
import { Task, deleteTask } from "../models/tasks";

import './TaskRow.css';
import { useDispatch } from "react-redux";

export default function TaskRow(props: {
    task: Task
}) {
    const [ mouseOn, setMouseOn ] = useState<boolean>(false);
    const dispatch = useDispatch();

    const HandleDeleteBtn = () => {
        dispatch(deleteTask(props.task.id));
        setMouseOn(false);
    };

    return <tr  onClick={() => setMouseOn(true)} 
                className="task--row">
        <td>{props.task.id}</td>
        <td>{props.task.name}</td>
        <td>{props.task.description}</td>
        <td>{props.task.done ? "yes" : "no"}</td>
        {mouseOn ? 
        <div className="task--menu">
            <div className="task--menu-content">
                <h2>Details of {props.task.name}</h2>
                <p>
                    {props.task.description}
                </p>
                <button onClick={() => HandleDeleteBtn()}>Delete</button>
                <button onClick={() => setMouseOn(false)}>Close</button>
            </div>
        </div> : <></>}
    </tr>
}