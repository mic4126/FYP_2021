import React from "react";
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Link
} from "react-router-dom";

import IndexPageContent from "./IndexPageContent";

function IndexRouter(props: any) {
    return (
        <Routes>
            <Route path='/' element={<IndexPageContent />}> </Route>
        </Routes>
    );
}

export default IndexRouter;
