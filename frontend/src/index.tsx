import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Header from './header/header';
import IndexRouter from './IndexRouter';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios';
import { IntlProvider } from 'react-intl';
import lang_en from './lang/lang_en.json';

function IndexPage(props: any) {
  const [locale, setLocale] = useState(navigator.language);

  console.log(locale);

  let lang = {
    'en': lang_en
  }
  return (
    <React.StrictMode>
      <IntlProvider locale="en" messages={lang_en}>
        <BrowserRouter>
          <Header locale={locale} setLocale={setLocale} />
          <IndexRouter />
        </BrowserRouter>

      </IntlProvider>
    </React.StrictMode >)
}

axios.defaults.baseURL = `http://${process.env.REACT_APP_API_SERVER}`
ReactDOM.render(
  <IndexPage />
  ,
  document.getElementById('root')

);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
