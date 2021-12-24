import React, { useEffect, useState } from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Header from './header/header';
import IndexRouter from './IndexRouter';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios';
import { IntlProvider } from 'react-intl';
import en from './lang/en.json';
import zhTW from './lang/zh-TW.json'

function IndexPage(props: any) {
  const [locale, setLocale] = useState(navigator.language);
  const [langFile, setLangFile] = useState(en)
  console.log(locale);

  // let lang: { string: Record<string, string> } = { en: (new Record<string,string>).co) }

  useEffect(() => {
    switch (locale) {
      case 'zh-TW':
        setLangFile(zhTW)
        break;
      case 'en':
        setLangFile(en);
        break;
      default:
        setLangFile(en);
        break;
    }
  }, [locale])

  return (
    <React.StrictMode>
      <IntlProvider locale={locale} messages={langFile}>
        <BrowserRouter>
          <Header locale={locale} setLocale={setLocale} />
          <IndexRouter locale={locale} />
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
