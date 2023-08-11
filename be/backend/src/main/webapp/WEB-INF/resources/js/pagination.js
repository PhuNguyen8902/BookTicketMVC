//import {getData} from "./fetchData";

function pagination(page) {
    const queryParams = new URLSearchParams(window.location.search);
    queryParams.set("page", page);
    const newUrl = window.location.pathname + "?" + queryParams.toString();
    window.history.pushState({}, "", newUrl);
//    getData();
}

