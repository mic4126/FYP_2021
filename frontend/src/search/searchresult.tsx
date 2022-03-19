import axios from "axios";
import React, { useEffect, useState } from "react";
import { FormattedMessage } from "react-intl";
import { createSearchParams, useSearchParams } from "react-router-dom";
import { Link } from "react-router-dom";
import useRequestLang from "../util/useRequestLang";

const SearchResult = (props: any) => {
    const [searchParam, setSearchParam] = useSearchParams();
    const [searchResult, setSearchResult] = useState<SearchResult[]>([]);
    const [queryDone, setQueryDone] = useState(false);
    const query = searchParam.get('q');
    const tag = searchParam.get('tag');
    const tagDisplay = searchParam.get('display');
    console.log("Query:" + query);
    console.log("Tag:" + tag);

    const requestLang = useRequestLang()

    useEffect(() => {
        if (query) {

            axios.get<SearchResult[]>(`/search`, {
                params: {
                    q: query,
                    lang: requestLang
                }
            }).then((resp) => {
                console.log(resp.data);
                setQueryDone(true);
                setSearchResult(resp.data);
            }
            )
        }
    }, [requestLang, query])


    useEffect(() => {
        if (tag) {

            axios.get<SearchResult[]>(`/search/tag`, {
                params: {
                    q: tag,
                    lang: requestLang
                }
            }).then((resp) => {
                console.log(resp.data);
                setQueryDone(true);
                setSearchResult(resp.data);
            }
            )
        }
    }, [requestLang, tag])

    const listResult = (searchResult: SearchResult[]) => {
        if (queryDone) {
            if (searchResult.length !== 0) {
                return searchResult.map((e, index) => {
                    const searchParam = createSearchParams({ projectID: e.projectId + "" })
                    return (
                        <div className="card my-2" key={e.projectId}>
                            <div className="card-body ">
                                <h3 className="card-title mb-2"><Link to={{
                                    pathname: "/detail",
                                    search: "?" + searchParam.toString()
                                }}>{e.projectName}</Link></h3>
                                <div className="searchDesc">
                                    {e.projectDesc}
                                </div>
                            </div>
                        </div >
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
            <h1>Search Result - {getSearchDisplay()}</h1>
            {listResult(searchResult)}
        </div>
    )

    function getSearchDisplay(): React.ReactNode {
        if (query) {
            return query
        }
        if (tagDisplay) {
            return tagDisplay
        }
        return <FormattedMessage id="searchResult.All" defaultMessage={"ALL"} />
    }
}

export default SearchResult;