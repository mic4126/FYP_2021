import axios from "axios";
import React, { useEffect, useState } from "react";
import { Navigate, useNavigate } from "react-router";
import { createSearchParams } from "react-router-dom";
import { Tag } from "../modal/Tag.model";
import useTagLang from "../util/useTagLang";

const Tags = (props: any) => {
    const projectID = props.projectID
    const [tagData, setTagData] = useState<Tag[]>([])
    const navigate = useNavigate();
    const tagLang = useTagLang();

    useEffect(() => {
        axios.request<Tag[]>({
            url: `http://${process.env.REACT_APP_API_SERVER}/project/${projectID}/tag`
        }).then((resp) => {
            console.log(resp.data);
            setTagData(resp.data);
        })
    }, [projectID])

    const tagClickhandler = (e: React.MouseEvent<HTMLButtonElement>) => {
        console.log(e.currentTarget.dataset.tag);
        navigate({ pathname: `/search?${createSearchParams({ tag: e.currentTarget.dataset.tag + "", display: e.currentTarget.innerText })}` })
    }

    const listTag = (tagData: Tag[]) => {
        const result = tagData.map((tag, index) => {
            return <button type="button" className="btn btn-outline-primary mx-2 my-2" key={tag.tag + index} data-tag={tag.tag} onClick={tagClickhandler}>{tag[tagLang]}</button>

        })
        console.log(result);
        return result;
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