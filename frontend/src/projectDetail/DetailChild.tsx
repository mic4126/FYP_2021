import axios from "axios";
import { useEffect, useState } from "react";
import { Accordion } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { Attachment } from "../modal/Attachment.model";
import useDetailName from "../util/useDetailLang";

const listAttachment = (attachments: Attachment[]) => {
    attachments.map((atch) => {
        return <a href={"/api/project/attachment/" + atch.attachmentId}>{atch.origFileName + "." + atch.origExt}</a>
    })
}

export default function DetailChild(props: any) {
    const d: Detail = props.detail
    const [attachments, setAttachments] = useState([] as Attachment[])
    useEffect(() => {
        axios.get<Attachment[]>(`/project/detail/${d.detailId}/attachment`, {
        }).then(resp => {
            console.log(resp.data);
            setAttachments(resp.data)
        })
    }, [d.detailId])

    const detailLang = useDetailName();

    return (
        <Accordion.Item eventKey={d.detailId + '-detail'} key={d.detailId + '-item'}>
            <Accordion.Header key={d.detailId + '-header'}>
                {d[detailLang.name]}
            </Accordion.Header>
            <Accordion.Body key={d.detailId + '-body'}>
                <h3 className="my-2">
                    <FormattedMessage id="detail.desc" defaultMessage="Description" />
                </h3>
                <pre>
                    {d[detailLang.desc]}
                </pre>
                <h3 className="my-2">
                    <FormattedMessage id="detail.attachment" defaultMessage="Attachment" />
                </h3>
                {listAttachment(attachments)}
            </Accordion.Body>
        </Accordion.Item>)
}