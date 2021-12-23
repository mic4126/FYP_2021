import React, { useEffect, useState } from "react";
import axios from "axios";
import Accordion from 'react-bootstrap/Accordion'


const Detail = (props: any) => {
    const projectID = props.projectID
    const [data, setData] = useState<Detail[]>([])
    console.log("projectID:" + projectID);

    useEffect(() => {
        const data = axios.request<Detail[]>({
            baseURL: `http://${process.env.REACT_APP_API_SERVER}/project/${projectID}/detail`
        }).then((resp) => {
            console.log(resp.data)
            setData(resp.data);

        })

    }, [])

    const listdetailChild = (data: Detail[]) => {
        return data.map((d, index) => {
            return (
                <Accordion.Item eventKey={index + ''} key={d.detailID}>
                    <Accordion.Header>
                        {d.detailName}
                    </Accordion.Header>
                    <Accordion.Body>
                        {d.detailDesc}
                    </Accordion.Body>
                </Accordion.Item>
            )
        })

    }

    return (
        <div className="card my-2">
            <div className="card-body">
                <h2 className="card-title">Details</h2>
                <Accordion>
                    {listdetailChild(data)}
                </Accordion>

            </div>
        </div>
    )
}

export default Detail