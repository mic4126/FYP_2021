import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { Link } from "react-router-dom";

const SearchResult = (props: any) => {
    const [searchParam, setSearchParam] = useSearchParams();
    const [searchResult, setSearchResult] = useState<SearchResult[]>([]);
    const [queryDone, setQueryDone] = useState(false);
    const query = searchParam.get('q');
    console.log("Query:" + query);

    useEffect(() => {
        axios.get<SearchResult[]>(`/project/search`, { params: { q: query } }).then((resp) => {
            console.log(resp.data);
            setQueryDone(true);
            setSearchResult(resp.data);
        })
    }, [])

    const listResult = (searchResult: SearchResult[]) => {
        if (queryDone) {
            if (searchResult.length !== 0) {
                return searchResult.map((e, index) => {
                    return (
                        <div className="card my-2" key={e.projectID}>
                            <div className="card-body ">
                                <h3 className="card-title mb-2"><Link to={"/detail?projectID=" + e.projectID}>{e.projectName}</Link></h3>
                                <div className="searchDesc">
                                    {e.projectDesc}
                                </div>
                            </div>
                        </div>
                    )
                })
            }
            return (
                <div className="d-flex justify-content-center align-items-center">
                    <h1>No result.</h1>
                </div>
            )


        } else {
            return (
                <div className="d-flex justify-content-center align-items-center" key="LoadingSpinner">
                    <div className="spinner-border loading-spinner" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                </div>
            )

        }
    }

    return (

        <div className="container">
            <h1>Search Result - {query ? query : "All"}</h1>
            {listResult(searchResult)}
        </div>
    )
}

export default SearchResult;