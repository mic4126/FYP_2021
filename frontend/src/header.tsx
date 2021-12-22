import React from "react";
import './header.css'

function Header(props: any) {

    return (
        <header className="p-3 mb-3">
            <div className="container">
                <div className="d-flex flex-wrap align-items-center justify-content-lg-start">
                    <a href="/" className="d-flex align-item-center flex-grow-1 mb-lg-0 text-dark text-decoration-none" id="logo">
                        AI Algorithm Registry
                    </a>
                    <div className="d-flex flex-wrap align-items-center justify-content-lg-start searchbar">
                        <form className="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3 ">
                            <input type="search" className="form-control" placeholder="Search..." aria-label="Search"></input>
                        </form>
                    </div>

                    <div id="langs" className="d-flex flex-wrap align-items-start justify-content-lg-start">
                        <a href="" onClick={(e) => { e.preventDefault(); props.setLocale('ENG') }} id="eng" className="link-secondary">English </a>/
                        <a href="" onClick={(e) => { e.preventDefault(); props.setLocale('TC') }} id="tc" className="link-secondary">繁</a> /
                        <a href="" id="sc" onClick={(e) => { e.preventDefault(); props.setLocale('SC') }} className="link-secondary">簡</a>
                    </div>

                </div>
            </div>
        </header>
    )
}

export default Header;