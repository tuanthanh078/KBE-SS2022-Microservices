import { sendHtmlRequest } from "./main/Main.js";

const ENDPOINT_CURRENCY = "/currency";

const CURRENCY_US_DOLLAR = "USD";
const CURRENCY_EURO = "EUR";
const CURRENCY_POUND = "GBP";
const CURRENCY_YEN = "JPY";
const CURRENCY_FRANCS = "CHF";

export function price(url, onReceive, onFail, value, currency){
    if(currency === CURRENCY_US_DOLLAR)return;
    let requestParams =
        "?amount="+value+
        "&currentCurrency="+CURRENCY_US_DOLLAR+
        "&newCurrency="+currency;
    sendHtmlRequest("GET", url + ENDPOINT_CURRENCY + requestParams, onReceive, onFail);
}

export function currencySymbol(currency){
    switch(currency){
        case CURRENCY_EURO:
            return '€';
        case CURRENCY_POUND:
            return '£';
        case CURRENCY_YEN:
            return '¥';
        case CURRENCY_FRANCS:
            return 'CHF';
        default:
            return '$';
    }
}