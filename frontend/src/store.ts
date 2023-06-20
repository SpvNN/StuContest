import { configureStore } from "@reduxjs/toolkit";
import userSlice from "./models/user_slice";
import tasksSlice from "./models/tasks";
import serverMiddleware from "./middleware/server-midlewara";
import { setupListeners } from "@reduxjs/toolkit/dist/query";


const store = configureStore({
    reducer: {
        user: userSlice,
        tasks: tasksSlice,
        [serverMiddleware.reducerPath]: serverMiddleware.reducer

    },

    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware()
            .concat(serverMiddleware.middleware)
});


// NOTE: For reconecting and so on, just live like that for now
setupListeners(store.dispatch);

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
export { store };
