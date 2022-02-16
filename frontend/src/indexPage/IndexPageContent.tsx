import React, { useContext, useEffect, useState } from 'react';
import logo from '../logo.svg'
import { useSearchParams, Link, useNavigate, createSearchParams } from 'react-router-dom';
import ProjectDetail from '../projectDetail/projectDetail';
import { Project } from '../modal/Project';

import './IndexPageContent.css'
import { useIntl } from 'react-intl';
import IndexPageProject from './IndexPageProject';
import useRequestLang from '../util/useRequestLang';
import { LangContext } from '../context/lang-context';
import axios from 'axios';

function IndexPageContent(props: any) {
  let [searchParams, setSearchParams] = useSearchParams();
  const navigate = useNavigate();
  const intl = useIntl();
  const langContext = useContext(LangContext)
  const requsetLang = useRequestLang();

  let [projects, setProjects] = useState([] as Project[]);

  useEffect(() => {
    axios.get<Project[]>(`/project`, {
      params: {
        lang: requsetLang
      }
    }).then(resp => {
      console.log(resp.data);
      setProjects(resp.data);
    })

  }, [requsetLang])




  return (
    <div className="row d-flex align-items-center align-self-center vertical-center">
      {
        projects.map((project, idx, projectArray) => {
          return <IndexPageProject project={project} key={"p-" + idx} />
        })
      }

      {/* <div className="card col-6 mx-auto my-2" style={{ width: '18rem' }}>
        <img src={logo} className="card-img-top" alt="..."></img>
        <div className="card-body">
          <h5 className="card-title">Card title</h5>
          <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" className="btn btn-primary">Go somewhere</a>
        </div>
      </div>
      <div className="card col-6 mx-auto my-2" style={{ width: '18rem' }}>
        <img src={logo} className="card-img-top" alt="..."></img>
        <div className="card-body">
          <h5 className="card-title">Card title</h5>
          <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" className="btn btn-primary">Go somewhere</a>
        </div>
      </div>
      <div className="card col-6 mx-auto my-2" style={{ width: '18rem' }}>
        <img src={logo} className="card-img-top" alt="..."></img>
        <div className="card-body">
          <h5 className="card-title">Card title</h5>
          <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <a href="#" className="btn btn-primary">Go somewhere</a>
        </div>
      </div> */}
    </div>
  );
}

export default IndexPageContent;
