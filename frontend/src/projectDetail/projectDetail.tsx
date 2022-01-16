import React, { useContext, useEffect, useState } from "react";
import Carousel from 'react-bootstrap/Carousel'
import logo from './../logo.svg';
import Detail from "./Detail";
import { useSearchParams } from 'react-router-dom'
import Tags from './Tags'
import axios from "axios";
import { LangContext } from "../context/lang-context";
import useRequestLang from "../util/useRequestLang";
import { FormattedMessage } from "react-intl";
import { Contact } from "../modal/Contact.model";
import { Attachment } from "../modal/Attachment.model";
import "./projectDetail.scss"
import useContactLang from "../util/useContactLang";

const ProjectDetail = (props: any) => {
    const [searchParams, setSearchParams] = useSearchParams()
    const projectID = searchParams.get('projectID')
    const departmentLang = useContactLang()

    console.log("projectID:" + projectID);

    let requestLang = useRequestLang();

    const [desc, setDesc] = useState("")
    useEffect(() => {
        axios.get(`/project/desc`, {
            responseType: 'text',
            params: {
                projectId: projectID,
                lang: requestLang
            }
        }).then(resp => {
            console.log(resp);
            setDesc(resp.data)
        })
    }, [requestLang])

    const [projectName, setProjectName] = useState("")
    useEffect(() => {
        axios.get(`project/name`, {
            responseType: 'text',
            params: {
                projectId: projectID,
                lang: requestLang
            }
        }).then(resp => {
            console.log("Project Name: " + resp.data);
            setProjectName(resp.data)
        })
    }, [requestLang])

    const [contact, setContact] = useState(new Object() as Contact)
    useEffect(() => {
        axios.get<Contact>(`project/contact`, {
            params: {
                projectId: projectID,
                lang: requestLang
            }
        }).then(resp => {
            console.log(resp.data);
            setContact(resp.data)
        })
    }, [])

    const [attachments, setAttachments] = useState([] as Attachment[])
    useEffect(() => {
        axios.get<Attachment[]>(`project/${projectID}/attachment`, {
        }).then(resp => {
            console.log(resp.data);
            setAttachments(resp.data)
        })
    }, [])



    return (
        <div className="container-fluid">

            <div className="row justify-content-center">
                <div className="col container"></div>
                <div className="col-xxl-9">
                    <h1>{projectName}</h1>
                    <Carousel variant="dark" className="">
                        {
                            attachments.map((atch) => {
                                return (
                                    <Carousel.Item key={"atch" + atch.attachmentId}>
                                        <img
                                            className="d-block mx-auto"
                                            src={`/api/project/attachment/${atch.attachmentId}`}
                                            alt=""
                                        />
                                    </Carousel.Item>
                                )
                            })
                        }
                    </Carousel>

                    <div className="card my-2">
                        <div className="card-body w-100">
                            <h2 className="card-title"><FormattedMessage id="project.desc" defaultMessage={"Description"} /></h2>
                            {desc}
                        </div>
                    </div>

                    <div className="card my-2">
                        <div className="card-body">
                            <h2 className="card-title">
                                Link to Service
                            </h2>
                            <a href={contact.url}>{contact.url}</a>
                        </div>
                    </div>

                    <div className="card my-2">
                        <div className="card-body">
                            <h2 className="card-title w-100">
                                <FormattedMessage id="project.contact" defaultMessage={"Contact"} />
                            </h2>
                            <div className="d-flex flex-wrap">
                                <div className="card  mx-2 my-2">
                                    <div className="card-body">
                                        <h4 className="card-title"><FormattedMessage id="project.contact.department" defaultMessage={"Responsible Bureau/Department"} /></h4>
                                        {contact[departmentLang]}
                                    </div>
                                </div>
                                <div className="card  mx-2 my-2">
                                    <div className="card-body">
                                        <h4 className="card-title"><FormattedMessage id="project.contact.email" defaultMessage="Email" /></h4>
                                        <a href={"mailto:" + contact.email}>{contact.email}</a>
                                    </div>
                                </div>
                                <div className="card mx-2 my-2">
                                    <div className="card-body">
                                        <h4 className="card-title"><FormattedMessage id="project.contact.tel" defaultMessage="Telephone" /> </h4>
                                        <a href={"tel:+852" + contact.phonenumber}>{contact.phonenumber}</a>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>

                    <Detail projectID={projectID} />

                </div>
                <Tags projectID={projectID} />
            </div>
        </div>

    )

}

export default ProjectDetail