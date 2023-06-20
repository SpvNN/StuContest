import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import serverMiddleware from "../middleware/server-midlewara";

export interface Task {
    id: number
    name: string
    description: string
    done: boolean
}


const initialState: Array<Task> = [];


const taskSlice = createSlice({
    name: 'tasks',
    initialState,
    reducers: {
        createTask: (state: Array<Task>, task: PayloadAction<Task>) => {
            console.debug("Pushin new task");
            state.push(task.payload);
        },

        deleteTask: (state: Array<Task>, id: PayloadAction<number>) => {
            console.debug(`Deleting task with id=${id}`)
            const taskId: number = state.findIndex((e) => e.id === id.payload);
            if (taskId > -1) {
                console.debug("Item found! Removing...");
                state.splice(taskId, 1);
                console.debug("Item deleted!");
                return;
            }

            console.debug("Item not found! Skipping...");
        }
    },
    extraReducers: (builder) => {
        builder.addMatcher(
            serverMiddleware.endpoints.getTasks.matchFulfilled,
            (state: Array<Task>, { payload }) => {
                payload.forEach((el) => {
                    state.push(el);
                });
            }
        )
    }
})

export const { createTask, deleteTask } = taskSlice.actions;
export default taskSlice.reducer;
