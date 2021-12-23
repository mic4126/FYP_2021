import React from "react";
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link
} from "react-router-dom";
import ProjectDetail from "./projectDetail/projectDetail";

import IndexPageContent from "./IndexPageContent";

function IndexRouter(props: any) {
    return (
        <Routes>
            <Route path='/' element={<IndexPageContent/>}> </Route>
            <Route path='/detail' element={<ProjectDetail/>}></Route>
        </Routes>
    );
}

export default IndexRouter;
