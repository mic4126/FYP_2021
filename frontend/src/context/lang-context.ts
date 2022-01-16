import React from "react";

export const LangContext = React.createContext({
    lang: navigator.language,
    updateLang: (lang:string) => { }
})
