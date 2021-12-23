import React from 'react';
import logo from './logo.svg';
import { useSearchParams, Link, useNavigate, createSearchParams } from 'react-router-dom';
import ProjectDetail from './projectDetail/projectDetail';

import './IndexPageContent.css'

function IndexPageContent(props: any) {
  let [searchParams, setSearchParams] = useSearchParams();
  const navigate = useNavigate();
  return (
    <div className="row d-flex align-items-center align-self-center vertical-center">
      <div className="card col-6 mx-auto" style={{ width: '18rem' }}>
        <img src={logo} className="card-img-top" alt="..."></img>
        <div className="card-body">
          <h5 className="card-title">Car Plate Reconginition</h5>
          <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <button className="btn btn-primary" data-project-id="1"
            onClick={(e) => {
              if (e.target instanceof HTMLElement) {
                const projectID = e.target.dataset["projectId"] + ''
                navigate({ pathname: '/detail', search:`?${createSearchParams({projectID:projectID})}`})
              }
            }}
          >Read more</button>
        </div>
      </div>
      <div className="card col-6 mx-auto" style={{ width: '18rem' }}>
        <img src={logo} className="card-img-top" alt="..."></img>
        <div className="card-body">
          <h5 className="card-title">Card title</h5>
          <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" className="btn btn-primary">Go somewhere</a>
        </div>
      </div>
      <div className="card col-6 mx-auto" style={{ width: '18rem' }}>
        <img src={logo} className="card-img-top" alt="..."></img>
        <div className="card-body">
          <h5 className="card-title">Card title</h5>
          <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" className="btn btn-primary">Go somewhere</a>
        </div>
      </div>
      <div className="card col-6 mx-auto" style={{ width: '18rem' }}>
        <img src={logo} className="card-img-top" alt="..."></img>
        <div className="card-body">
          <h5 className="card-title">Card title</h5>
          <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" className="btn btn-primary">Go somewhere</a>
        </div>
      </div>
    </div>
  );
}

export default IndexPageContent;
