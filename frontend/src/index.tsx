import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import IndexPageContent from './IndexPageContent';
import Header from './header/header';
import IndexRouter from './IndexRouter';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from "react-router-dom";



import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios';


function IndexPage(props: any) {
  const [locale, setLocale] = useState(navigator.language);
  console.log(locale);
  return (
    <React.StrictMode>
      <BrowserRouter>
        <Header locale={locale} setLocale={setLocale} />
        <IndexRouter />
      </BrowserRouter>
    </React.StrictMode>)
}

axios.defaults.baseURL = `http://${process.env.REACT_APP_API_SERVER}`
ReactDOM.render(
  <IndexPage />,
  document.getElementById('root')

);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
