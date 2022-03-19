import React, { useEffect, useState } from "react";
import axios from "axios";
import Accordion from 'react-bootstrap/Accordion'
import useDetailName from "../util/useDetailLang";
import { FormattedMessage, useIntl } from "react-intl";
import { formatMessage } from "@formatjs/intl";
import { Attachment } from "../modal/Attachment.model";
import DetailChild from "./DetailChild";


const Detail = (props: any) => {
    const projectID = props.projectID
    const [data, setData] = useState<Detail[]>([])
    console.log("projectID:" + projectID);
    const detailLang = useDetailName();
    const intl = useIntl();

    useEffect(() => {
        const data = axios.request<Detail[]>({
            baseURL: `http://${process.env.REACT_APP_API_SERVER}/project/${projectID}/detail`
        }).then((resp) => {
            console.log(resp.data)
            setData(resp.data);

        })

    }, [projectID])

    const [attachments, setAttachments] = useState([] as Attachment[])
    useEffect(() => {
        axios.get<Attachment[]>(`project/${projectID}/attachment`, {
        }).then(resp => {
            console.log(resp.data);
            setAttachments(resp.data)
        })
    }, [projectID])

    const listdetailChild = (data: Detail[]) => {
        return data.map((d, index) => {
            return (
                <DetailChild detail={d} key={'detailId-'+d.detailId} />
            )
        })

    }

    return (
        <div className="card my-2">
            <div className="card-body">
                <h2 className="card-title"><FormattedMessage id="detail.detail" defaultMessage={"Details"} /></h2>
                <Accordion>
                    {listdetailChild(data)}
                </Accordion>

            </div>
        </div>
    )
}

export default Detail