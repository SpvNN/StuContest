import { useSelector } from "react-redux";
import { RootState } from "../store";
import { Task } from "../models/tasks";
import TaskRow from "./TaskRow";

export default function TasksList() {
    const tasks: Array<Task> = useSelector((state: RootState) => state.tasks);
    
    return <>
        <table>
            <tbody>
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Description</td>
                <td>Done</td>
            </tr>
            {tasks.map((task) => {
                return <TaskRow task={task}/>;
            })}
            </tbody>
        </table>
    </>
}
