import { useDispatch, useSelector } from "react-redux";
import { User } from "../../models/user_slice";
import { RootState } from "../../store";
import TasksList from "../../components/TasksTable";
import { useState } from "react";
import { Task, createTask } from "../../models/tasks";
import { getTasks } from "../../middleware/server-midlewara";

export default function PanelIndexPage() {
    const user: User = useSelector((state: RootState) => state.user);
    const tasks: Array<Task> = useSelector((state: RootState) => state.tasks);
    const dispatch = useDispatch();

    console.log(user.email);

    const { data, isLoading, isError } = getTasks.useQuery(user.email);

    // Form handling
    const [isPopupAppearing, setPopupAppearance] = useState<boolean>(false);

    // Popup data
    const [newTaskName, setNewTaskName] = useState<string>('');
    const [newTaskDesc, setNewTaskDesc] = useState<string>('');
    const [newTaskState, setNewTaskState] = useState<string>('');

    // Handle opening/closing of popup
    const createTaskHandler = () => {
        console.debug("Modifying view of popup");
        setPopupAppearance(!isPopupAppearing);
    };

    // Handling creation
    const createNewTask = () => {
        const id = tasks.length;
        const state = newTaskState === "Yes" ? true :
                      newTaskState === "No"  ? false :
                      false;
        dispatch(createTask({
            id: id,
            name: newTaskName,
            description: newTaskDesc,
            done: state
        }));
        console.debug("Created new task");
    };

    return <>
        <section>
            <div className="container">
                <h1>👋 Hello, {user.email}!</h1>
            </div>
        </section>
        <section>
            <div className="container">
                <h2>🚴‍♀️ Tasks for today</h2>
                {/* {isLoading ?
                    <h1>Loading...</h1> :
                    isError ? <>
                        <h1>Error appeared! Please login</h1>
                    </> :
                    <>
                        
                    </>
                } */}
                <button onClick={() => createTaskHandler()}>Create new task</button>
                <TasksList/>
            </div>
        </section>
        {
            isPopupAppearing ? 
            <div className="popup">
                <div className="popup--content">
                    <h1>Create new task</h1>
                    <input  placeholder="Name of task" 
                            onChange={(val) => setNewTaskName(val.target.value)} />
                    <textarea   placeholder="Enter description of tasks"
                                onChange={(val) => setNewTaskDesc(val.target.value)}/>
                    <select defaultValue={"Select option"}
                            onChange={(val) => setNewTaskState(val.target.value)}>
                        <option disabled={true}>Select option</option>
                        <option >Yes</option>
                        <option>No</option>
                    </select>
                    <div className="popup--toolbox">
                        <button onClick={() => createTaskHandler()}>Close</button>
                        <button onClick={() => createNewTask()}>Confirm</button>
                    </div>
                </div>
            </div> : <></>
        }
    </>
}
