import React, { useState } from "react";
import Carousel from 'react-bootstrap/Carousel'
import logo from './../logo.svg';
import Detail from "./Detail";
import { useSearchParams } from 'react-router-dom'
import Tags from './Tags'

const ProjectDetail = (props: any) => {
    const [searchParams, setSearchParams] = useSearchParams()
    const projectID = searchParams.get('projectID')
    console.log("projectID:" + projectID);

    return (
        <div className="container-fluid">

            <div className="row justify-content-center">
                <div className="col container"></div>
                <div className="col-xxl-9">
                    <h1>Car Plate Recognition</h1>
                    <Carousel variant="dark">
                        <Carousel.Item>
                            <img
                                className="d-block w-100"
                                src={logo}
                                alt="First slide"
                            />
                            <Carousel.Caption>
                                <h3>First slide label</h3>
                                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                            </Carousel.Caption>
                        </Carousel.Item>
                        <Carousel.Item>
                            <img
                                className="d-block w-100"
                                src={logo}
                                alt="Second Slide slide"
                            />
                            <Carousel.Caption>
                                <h3>Second slide label</h3>
                                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                            </Carousel.Caption>
                        </Carousel.Item>
                    </Carousel>

                    <div className="card my-2">
                        <div className="card-body w-100">
                            <h2 className="card-title">Description</h2>
                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                        </div>
                    </div>

                    <div className="card my-2">
                        <div className="card-body">
                            <h2 className="card-title">
                                Link to Service
                            </h2>
                            <a href="http://example.com">http://example.com</a>
                        </div>
                    </div>

                    <div className="card my-2">
                        <div className="card-body">
                            <h2 className="card-title w-100">
                                Contact Information
                            </h2>
                            <div className="d-flex flex-wrap">
                                <div className="card  mx-2 my-2">
                                    <div className="card-body">
                                        <h4 className="card-title">Responsible Bureau/Department</h4>
                                        Transport Department
                                    </div>
                                </div>
                                <div className="card  mx-2 my-2">
                                    <div className="card-body">
                                        <h4 className="card-title">Email</h4>
                                        <a href="mailto:">abc@abc.com</a>
                                    </div>
                                </div>
                                <div className="card mx-2 my-2">
                                    <div className="card-body">
                                        <h4 className="card-title">Telephone</h4>
                                        <a href="tel:+85212345678">12345678</a>
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