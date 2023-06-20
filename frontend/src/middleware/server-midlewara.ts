import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import { Task } from '../models/tasks';


const serverMiddleware = createApi({
    reducerPath: 'api',
    baseQuery: fetchBaseQuery({ baseUrl: '/api' }),
    endpoints: (builder) => ({
        loginUser: builder.query<void, { email: string, password: string }>({
            query: (user: { email: string, password: string }) => ({
                url: '/user/login',
                method: 'POST',
                body: user
            })
        }),

        // Get all tasks of user
        getTasks: builder.query<Array<Task>, string>({
            query: (body: string) => ({
                url: '/user/tasks',
                method: 'POST',
                body: {
                    email: body
                }
            })
        }),

        postTask: builder.query<null, {t: Task, e: string}>({
            query: (body: {t: Task, e: string}) => ({
                url: '/user/task',
                method: 'DELETE',
                body: {
                    email: body.e,
                    task: body.t
                }
            })
        })
    })
});


export const { loginUser, getTasks, postTask } = serverMiddleware.endpoints;
export default serverMiddleware;
