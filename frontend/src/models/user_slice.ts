import { PayloadAction, createSlice } from "@reduxjs/toolkit";


export interface User {
    email: string
}


const initialState: User = {
    email: "unknown"
}


const userSlice = createSlice({
    name: "user_clice",
    initialState,
    reducers: {
        changeEmail: (state: User, email: PayloadAction<string>) => {
            state.email = email.payload;
        },
    }
});

export const { changeEmail } = userSlice.actions;
export default userSlice.reducer;
