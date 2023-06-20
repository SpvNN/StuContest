import { BrowserRouter, Route, Routes } from "react-router-dom";
import DefaultOutler from "./outlets/Default";
import IndexPage from "./pages";
import SignupPage from "./pages/signup";
import LoginPage from "./pages/login";
import PanelIndexPage from "./pages/panel";


export default function AppRouter() {
    return <>
        <BrowserRouter>
            <Routes>
                {/* Default pages */}
                <Route element={<DefaultOutler/>}>
                    <Route path="/" element={<IndexPage/>}></Route>
                    <Route path="/signup" element={<SignupPage/>}></Route>
                    <Route path="/login" element={<LoginPage/>}></Route>

                    {/* Panels */}
                    <Route path="/panel" element={<PanelIndexPage/>}>
                        {/* <Route element={<PanelIndexPage/>}/> */}
                    </Route>
                </Route>
            </Routes>
        </BrowserRouter>
    </>
}
