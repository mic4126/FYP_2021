import axios from "axios";
import React, { useEffect, useState } from "react";
import { Navigate, useNavigate } from "react-router";
import { createSearchParams } from "react-router-dom";

const Tags = (props: any) => {
    const projectID = props.projectID
    const [tagData, setTagData] = useState<string[]>([])
    const navigate = useNavigate();

    useEffect(() => {
        axios.request<string[]>({
            url: `http://${process.env.REACT_APP_API_SERVER}/project/${projectID}/tag`
        }).then((resp) => {
            console.log(resp.data);
            setTagData(resp.data);
        })
    }, [])

    const tagClickhandler = (e:React.MouseEvent<HTMLButtonElement>) =>{
        console.log(e.currentTarget.dataset.tag);
        navigate({pathname:`/search?${createSearchParams({q: e.currentTarget.dataset.tag+""})}` })
    }

    const listTag = (tagData: string[]) => {
        return tagData.map((tag, index) => {
            return <button type="button" className="btn btn-outline-primary mx-2 my-2" key={tag + index} data-tag={tag} onClick={tagClickhandler}>{tag}</button>

        })
    }

    return (
        <div className="container col">
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title">Tags:</h5>
                    <div className="d-flex flex-wrap col">
                        {listTag(tagData)}
                    </div>
                </div>
            </div>
        </div>
    )

}

export default Tags