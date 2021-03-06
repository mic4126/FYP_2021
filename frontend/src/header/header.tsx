import React, { useContext, useState } from "react";
import { FormattedMessage, useIntl } from "react-intl";
import { createSearchParams, useNavigate } from "react-router-dom";
import { LangContext } from "../context/lang-context";
import './header.scss'

function Header(props: any) {
    const navigate = useNavigate();
    const [searchQuery, setSearchQuery] = useState<string>("");
    const searchHandler = (e: React.MouseEvent<HTMLButtonElement>) => {
        navigate({
            pathname: '/search',
            search: `?${createSearchParams({ q: searchQuery })}`
        })
    }
    const intl = useIntl();
    const {lang,updateLang} = useContext(LangContext);



    return (

        <header className="p-3 mb-3">
            <div className="container">
                <div className="d-flex flex-wrap align-items-center justify-content-lg-start">
                    <a href="/" className="d-flex align-item-center flex-grow-1 mb-lg-0 text-dark text-decoration-none" id="logo">
                        AI Algorithm Registry
                    </a>
                    <div className="d-flex flex-wrap align-items-center justify-content-lg-start searchbar">
                        <form className="w-100">
                            <div className="input-group">
                                <input id="searchInput" type="search" className="form-control" aria-label="Search" placeholder={intl.formatMessage({
                                    id: 'header.searchPlaceHolder',
                                    description:'placeholder text for search bar input'
                                })}
                                    value={searchQuery} onInput={e => setSearchQuery(e.currentTarget.value)} />
                                <button type="button" className="btn btn-outline-primary" onClick={searchHandler}><FormattedMessage id="header.search" defaultMessage={"Search"}/></button>
                            </div>
                        </form>
                    </div>

                    <div id="langs" className="d-flex flex-wrap align-items-start justify-content-lg-start">
                        <a href="" onClick={(e) => { e.preventDefault(); props.setLocale('en') }} id="eng" className="link-secondary">English </a>/
                        <a href="" onClick={(e) => { e.preventDefault(); props.setLocale('zh-TW') }} id="tc" className="link-secondary">???</a> /
                        <a href="" id="sc" onClick={(e) => { e.preventDefault(); props.setLocale('zh-SC') }} className="link-secondary">???</a>
                    </div>

                </div>
            </div>
        </header>
    )
}

export default Header;