import { useContext, useEffect, useState } from "react";
import { LangContext } from "../context/lang-context";
import { Tag } from "../modal/Tag.model";


// return index value to get correct lang in tag modal 
export default function useTagLang() {
    const langContext = useContext(LangContext);
    const [tagLang, setTaglang] = useState(getTagLang(langContext.lang))
    useEffect(() => {
        console.log("useRequestLang Switch");
        let t = getTagLang(langContext.lang)
        setTaglang(t);

    }, [langContext.lang])
    console.log("useRequestLang Before Return");
    return tagLang;
}

function getTagLang(lang: string): keyof Tag {
    let tL: keyof Tag
    switch (lang) {
        case 'zh-TW':
            tL = "tagTC"
            break
        case 'zh-SC':
            tL = "tagSC"
            break
        default:
        case 'en':
            tL = "tag"
            break
    }
    return tL
}
