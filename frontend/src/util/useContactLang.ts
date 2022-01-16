import { useContext, useEffect, useState } from "react";
import { LangContext } from "../context/lang-context";
import { Contact } from "../modal/Contact.model";

export default function useRequestLang() {
    const langContext = useContext(LangContext);
    const [contactLang, setContactLang] = useState("" as keyof Contact)
    useEffect(() => {        
        let l = getReqLang(langContext.lang)
        setContactLang(l);

    }, [langContext.lang])    
    return contactLang;
}

function getReqLang(lang: string):keyof Contact {
    switch (lang) {
        case 'zh-TW':
            return "department_TC"
            break
        case 'zh-SC':
            return "department_SC"
            break
        default:
        case 'en':
            return "department"
            break
    }
}
