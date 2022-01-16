import { useContext, useEffect, useState } from "react";
import { LangContext } from "../context/lang-context";
import { Tag } from "../modal/Tag.model";

interface detailNameObj {
    name:keyof Detail,
    desc:keyof Detail,
}

// return index value to get correct lang in tag modal 
export default function useDetailName() {
    const langContext = useContext(LangContext);
    const [detailNameLang, setDetailNameLang] = useState(getDetailNameLang(langContext.lang))

    useEffect(() => {
        console.log("useRequestLang Switch");
        let t = getDetailNameLang(langContext.lang)
        setDetailNameLang(t);

    }, [langContext.lang])
    console.log("useRequestLang Before Return");
    return detailNameLang;
}

function getDetailNameLang(lang: string): detailNameObj {
    let d: detailNameObj
    switch (lang) {
        case 'zh-TW':
            d = {name:"detailName_TC",desc:"detailDesc_TC"}
            break
        case 'zh-SC':
            d = {name:"detailName_SC",desc:"detailDesc_SC"}
            break
        default:
        case 'en':
            d = {name:"detailName",desc:"detailDesc"}
            break
    }
    return d
}
