import React from "react";
import { useIntl } from "react-intl";
import { createSearchParams, useNavigate } from "react-router-dom";
import logo from '../logo.svg'
import { Project } from "../modal/Project";








export default function (props: any) {

    const navigate = useNavigate();
    const intl = useIntl();
    const project: Project = props.project;

    return (
        <div className="card col-6 mx-auto my-2" style={{ width: '18rem' }}>
            <img src={logo} className="card-img-top" alt="..."></img>
            <div className="card-body">
                <h5 className="card-title">{project.projectName}</h5>
                <p className="card-text">{project.projectDesc}</p>
                <button className="btn btn-primary" data-project-id={project.projectId}
                    onClick={(e) => {
                        if (e.target instanceof HTMLElement) {
                            const projectID = e.target.dataset["projectId"] + ''
                            navigate({ pathname: '/detail', search: `?${createSearchParams({ projectID: projectID })}` })
                        }
                    }}
                >{intl.formatMessage({ defaultMessage: "Read more", id: 'index.readMore' })}</button>
            </div>
        </div>)
}