import { useContext, useEffect, useState } from "react";
import { LangContext } from "../context/lang-context";

export default function useRequestLang() {
    const langContext = useContext(LangContext);
    const [requestLang, setRequestLang] = useState(getReqLang(langContext.lang))
    useEffect(() => {
        console.log("useRequestLang Switch");
        let l = getReqLang(langContext.lang)
        setRequestLang(l);

    }, [langContext.lang])
    console.log("useRequestLang Before Return");
    return requestLang;
}

function getReqLang(lang: string) {
    switch (lang) {
        case 'zh-TW':
            return "TC"
            break
        case 'zh-SC':
            return "SC"
            break
        default:
        case 'en':
            return "ENG"
            break
    }
}
