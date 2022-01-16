import {
    Routes,
    Route
} from "react-router-dom";
import ProjectDetail from "./projectDetail/projectDetail";


import IndexPageContent from "./indexPage/IndexPageContent";
import SearchResult from "./search/searchresult";

function IndexRouter(props: any) {
    const locale: string = props.locale;
    return (
        <Routes>
            <Route path='/' element={<IndexPageContent />}> </Route>
            <Route path='/detail' element={<ProjectDetail />}></Route>
            <Route path='/search' element={<SearchResult />}></Route>
        </Routes>
    );
}

export default IndexRouter;
