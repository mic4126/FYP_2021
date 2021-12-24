import React from "react";
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link
} from "react-router-dom";
import ProjectDetail from "./projectDetail/projectDetail";


import IndexPageContent from "./IndexPageContent";
import SearchResult from "./search/searchresult";

function IndexRouter(props: any) {
    return (
        <Routes>
            <Route path='/' element={<IndexPageContent/>}> </Route>
            <Route path='/detail' element={<ProjectDetail/>}></Route>
            <Route path='/search' element={<SearchResult/>}></Route>
        </Routes>
    );
}

export default IndexRouter;
